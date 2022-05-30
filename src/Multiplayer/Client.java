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
    public boolean createLobby = false;

    public Client(String userName){
        this.userName = userName;
    }
    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1",9999);
            write = new PrintWriter(client.getOutputStream(),true);
            read = new BufferedReader(new InputStreamReader(client.getInputStream()));
            Handler inHandler = new Handler();
            Thread t = new Thread(inHandler);
            t.start();


        } catch (IOException e) {
            throw new RuntimeException(e);
            //TODO:shutdown
        }
    }

    public void createLobby(){
        //createLobby = true;
        write.println( "CreateLobby" + ' ' + userName );
    }
    class Handler implements Runnable{

        @Override
        public void run() {
            while (!done){
                while (createLobby){
                    write.println( "CreateLobby" + ' ' + userName );
                    createLobby  = false;
                }
            }
        }
    }
}
