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
                            lobby.player1 = messageSplit[1];
                            lobby.level = messageSplit[2];
                        }
                    }else if(message.startsWith("JoinLobby")){
                        String[] messageSplit = message.split(" ",3);
                        if(messageSplit.length == 3){
                            mySqlDatabase.joinLobby(messageSplit[1],messageSplit[2]);
                            lobby.player2 = messageSplit[2];
                            for (ConnectionHandler ch : connections){
                                if(ch.lobby.player1 == messageSplit[2]){
                                    ch.write.println("JoinedLobby");
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
                    }
                }
            } catch (Exception e) {
                try {
                    shutdown();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        public void shutdown() throws SQLException {
            mySqlDatabase.leaveLobby(lobby.player1);
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
