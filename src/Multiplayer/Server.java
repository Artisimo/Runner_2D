package Multiplayer;


import Enviroment.mySqlDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server implements Runnable{
    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private ExecutorService thredpool;
    private boolean done;

    public Server(){
        connections = new ArrayList<>();
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9999);
            thredpool = Executors.newCachedThreadPool();
            while(!done) {
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                thredpool.execute(handler);
            }
        } catch (IOException e) {
            shutDown();
        }
    }

    public  void shutDown(){
        if(!server.isClosed()){
            try {
                done = true;
                for (ConnectionHandler ch : connections){
                    ch.shutdown();
                    mySqlDatabase.deleteLobby(ch.lobby.player1);
                }
                server.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class ConnectionHandler implements Runnable{

        private Socket client;
        private BufferedReader read;
        private PrintWriter write;
        private String clientName;
        public Lobby lobby = new Lobby();


        public ConnectionHandler(Socket client){
            this.client = client;
        }


        @Override
        public void run() {
            try {
                write = new PrintWriter(client.getOutputStream(), true);
                read = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String message;
                while((message = read.readLine()) != null){
                    if(message.startsWith("CreateLobby")){
                        String[] messageSplit = message.split(" ",3);
                        if(messageSplit.length == 3){
                            mySqlDatabase.createLobby(messageSplit[1],messageSplit[2]);
                            clientName = messageSplit[1];
                            lobby.player1 = messageSplit[1];
                            lobby.level = messageSplit[2];
                        }
                    }else if(message.startsWith("JoinLobby")){
                        String[] messageSplit = message.split(" ",3);
                        if(messageSplit.length == 3){
                            mySqlDatabase.joinLobby(messageSplit[1],messageSplit[2]);
                            clientName = messageSplit[2];
                            lobby.player2 = messageSplit[2];
                            String player = mySqlDatabase.getSpecificPlayer1(messageSplit[1]);
                            lobby.player1 = player;
                            for (ConnectionHandler ch : connections){
                                if(player.equals(ch.lobby.player1) && player.equals((ch.clientName))){
                                    ch.write.println("JoinedLobby");
                                    ch.lobby.player1 = player;
                                    ch.lobby.player2 = clientName;
                                    lobby.level = ch.lobby.level;
                                    break;
                                }
                            }
                        }
                    }else if(message.startsWith("Finished")){
                        String[] messageSplit = message.split(" ",2);
                        for (ConnectionHandler ch : connections){
                            if(ch.lobby.player1.equals(messageSplit[1]) || ch.lobby.player2.equals(messageSplit[1])){
                                //ch.lobby.finished = true;
//                                if(ch.lobby.player1.equals(messageSplit[1])){
//                                    mySqlDatabase.deleteLobby(ch.lobby.player1);
//                                }else {
//                                    mySqlDatabase.deleteLobby(ch.lobby.player2);
//                                }
                                ch.write.println("Finished" + ' ' + messageSplit[1]);
                                break;
                            }
                        }
                    }else if(message.startsWith("Score")){
                        String[] messageSplit = message.split(" ",3);
                        for(ConnectionHandler ch : connections){
                            if(messageSplit[2].equals(ch.lobby.player1)){
                                ch.lobby.player1Score = Integer.parseInt(messageSplit[1]);
                            }else if(messageSplit[2].equals(ch.lobby.player1)){
                                ch.lobby.player2Score = Integer.parseInt(messageSplit[1]);
                            }
                            if(ch.lobby.player1Score > 0 && ch.lobby.player2Score > 0){
                                if(ch.lobby.player1Score >= ch.lobby.player2Score){
                                    if(ch.lobby.player1.equals(messageSplit[2])){
                                        ch.write.println("Won");
                                    }else {ch.write.println("Lost");}
                                }else {
                                    if(!ch.lobby.player1.equals(messageSplit[2])){
                                        ch.write.println("Won");
                                    }else {ch.write.println("Lost");}
                                }
                            }
                        }
                    } else if(message.startsWith("LeaveLobby")){
                        String[] messageSplit = message.split(" ",2);
                        for (ConnectionHandler ch : connections){
                            if(ch.lobby.player2 != null && ch.clientName != null ){
                                if(ch.lobby.player2.equals(messageSplit[1]) && !ch.clientName.equals(messageSplit[1])){
                                    ch.lobby.player2 = null;
                                    this.lobby.player1 = null;
                                    this.lobby.player2 = null;
                                    mySqlDatabase.leaveLobby(messageSplit[1]);
                                    ch.write.println("LeftLobby");
                                    break;
                                }
                            }
                            if(ch.lobby.player1 != null && ch.clientName != null){
                                if(ch.lobby.player1.equals(messageSplit[1])){
                                    System.out.println(messageSplit[1]);
                                    mySqlDatabase.deleteLobby(messageSplit[1]);
                                    if(!ch.clientName.equals(messageSplit[1])){
                                        ch.write.println("LobbyDeleted");
                                    }
                                }
                            }
                        }
                    }else if(message.startsWith("StartGame")){
                        String[] messageSplit = message.split(" ",2);
                        if(clientName != null && lobby.player1 != null && lobby.player2 != null){
                            write.println("StartGame");
                        }
                        for (ConnectionHandler ch : connections){
                            if(ch.clientName != null && ch.lobby.player1 != null && ch.lobby.player2 != null){
                                if((ch.lobby.player1.equals(messageSplit[1]) || ch.lobby.player2.equals(messageSplit[1])) && !ch.clientName.equals(messageSplit[1])){
                                    ch.write.println("StartGame");
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                try {
                    shutdown();
                    System.out.println("Shutdown");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        public void print(){
            for (ConnectionHandler ch : connections){
                System.out.println(ch.clientName);
                System.out.println(ch.lobby.player1);
                System.out.println(ch.lobby.player2);
                System.out.println();
            }
        }
        public void shutdown() throws SQLException {
            for (ConnectionHandler ch : connections){
                if(ch.lobby.player2 != null && ch.lobby.player2.equals(clientName) && !ch.clientName.equals(clientName)){
                    ch.lobby.player2 = null;
                    this.lobby.player1 = null;
                    this.lobby.player2 = null;
                    mySqlDatabase.leaveLobby(clientName);
                    ch.write.println("LeftLobby");
                    break;
                }
                if(ch.lobby.player1 != null &&  ch.lobby.player1.equals(clientName)){
                    mySqlDatabase.deleteLobby(clientName);
                    if(!ch.clientName.equals(clientName)){
                        ch.write.println("LobbyDeleted");
                    }
                }
            }
            if(!client.isClosed()){
                try {
                    read.close();
                    write.close();
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            connections.remove(this);
        }
    }

    public static void main(String[] args){
        Server server = new Server();
        server.run();
    }
}
