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


public class Server implements Runnable{
    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;

    public Server(){
        connections = new ArrayList<>();
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9999);
            while(!done) {
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
            }
        } catch (IOException e) {
            //TODO:shutdown
        }
    }


    class ConnectionHandler implements Runnable{

        private Socket client;
        private BufferedReader read;
        private PrintWriter write;
        private String clientName;
        Lobby lobby;


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
                        String[] messageSplit = message.split(" ",2);
                        System.out.println("Ok");
                        if(messageSplit.length == 2){
                            mySqlDatabase.createLobby(messageSplit[1]);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args){
        Server server = new Server();
        server.run();
    }
}
