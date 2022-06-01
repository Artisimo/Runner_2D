package Multiplayer;

import Enviroment.mySqlDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;

public class Client implements Runnable{
    private Socket client;
    private boolean done = false;
    private BufferedReader read;
    private PrintWriter write;
    private String userName;
    public boolean isAction = false;
    public boolean gameFinished = false;


    public Client(String userName) {
        this.userName = userName;

    }
    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1",9999);
            write = new PrintWriter(client.getOutputStream(),true);
            read = new BufferedReader(new InputStreamReader(client.getInputStream()));
            InHandler inHandler = new InHandler();
            Thread t = new Thread(inHandler);
            t.start();


        } catch (Exception e) {
            try {
                shutdown();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }

    public void createLobby(String level){
        write.println( "CreateLobby" + ' ' + userName + ' ' + level );
    }
    public void joinLobby(int id){
        write.println("JoinLobby" + ' ' + Integer.toString(id) + ' ' + userName);
    }
    public void leaveLobby(){write.println("LeaveLobby" + ' ' + userName);}

    public void finishedGame(){
        write.println("Finished" + userName);
    }

    public void shutdown() throws IOException {
        done = true;
        try {
            if(read != null){
                read.close();
            }

            if(write != null){
                write.close();
            }
            mySqlDatabase.leaveLobby(userName);
            mySqlDatabase.deleteLobby(userName);

            if(client != null){
                if(!client.isClosed()){
                    client.close();
                }
            }

        }catch  (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        class InHandler implements Runnable{

        @Override
        public void run() {
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
            while (!done){
                try {
                    String message;
                    while ((message = read.readLine()) != null){
                        if (message.equals("JoinedLobby")) {
                            isAction = true;
                        }
                        if(message.equals("LeftLobby")){
                            isAction = true;
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
