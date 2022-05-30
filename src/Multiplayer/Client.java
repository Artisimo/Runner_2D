package Multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{
    private Socket client;
    private boolean done = false;
    private BufferedReader read;
    private PrintWriter write;
    private String userName;

    public Client(String userName){
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


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createLobby(String level){
        write.println( "CreateLobby" + ' ' + userName + ' ' + level );
    }
    public void joinLobby(String player_1){
        write.println("JoinLobby" + ' ' + player_1 + ' ' + userName);
    }

    public void shutdown() throws IOException {
        done = true;
        try {
            read.close();
            write.close();
            if(!client.isClosed()){
                client.close();
            }
        }catch (IOException e){
        }
    }

        class InHandler implements Runnable{

        @Override
        public void run() {
            while (!done){

            }
        }
    }
}
