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
            throw new RuntimeException(e);
            //TODO:shutdown
        }
    }


    class ConnectionHandler implements Runnable{

        private Socket client;
        private BufferedReader read;
        private PrintWriter write;
        private String clientName;
        //Lobby lobby;


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
                        }
                    }else if(message.startsWith("JoinLobby")){
                        String[] messageSplit = message.split(" ",3);
                        if(messageSplit.length == 2){
                            mySqlDatabase.joinLobby(messageSplit[1],messageSplit[2]);
                        }
                    }
                }
            } catch (Exception e) {
                shutdown();
            }
        }
        public void shutdown(){
            if(!client.isClosed()){
                try {
                    read.close();
                    write.close();
                    client.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args){
        Server server = new Server();
        server.run();
    }
}
