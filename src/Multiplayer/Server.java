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
                                    break;
                                }
                            }
                        }
                    }else if(message.startsWith("Finished")){
                        String[] messageSplit = message.split(" ",2);
                        for (ConnectionHandler ch : connections){
                            if(ch.lobby.player1 == messageSplit[1] || ch.lobby.player2 == messageSplit[1]){
                                ch.lobby.finished = true;
                                break;
                            }
                        }
                    }else if(message.startsWith("LeaveLobby")){
                        String[] messageSplit = message.split(" ",2);
                        for (ConnectionHandler ch : connections){
                            if(ch.lobby.player2.equals(messageSplit[1]) && !ch.clientName.equals(messageSplit[1])){
                                ch.lobby.player2 = null;
                                this.lobby.player1 = null;
                                this.lobby.player2 = null;
                                mySqlDatabase.leaveLobby(messageSplit[1]);
                                //mySqlDatabase.deleteLobby(messageSplit[1]);
                                ch.write.println("LeftLobby");
                                System.out.println("LeftLobby");
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                try {
                    System.out.println(clientName);
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
            mySqlDatabase.leaveLobby(lobby.player2);
            mySqlDatabase.deleteLobby(lobby.player1);
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
