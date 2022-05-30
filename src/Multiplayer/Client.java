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
    public boolean createLobby = true;

    public Client(String userName){
        this.userName = userName;
    }
    @Override
    public void run() {
        try {
            client = new Socket("127.0.0.1",9999);
            write = new PrintWriter(client.getOutputStream(),true);
            read = new BufferedReader(new InputStreamReader(client.getInputStream()));
            InputHandler inHandler = new InputHandler();
            Thread t = new Thread(inHandler);
            t.start();

            while (createLobby){
                createLobby  = false;
                System.out.println( "CreateLobby" + ' ' + userName );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
            //TODO:shutdown
        }
    }

    public void createLobby(){
        createLobby = true;
    }
    class InputHandler implements Runnable{

        @Override
        public void run() {
        }
    }


    public static void main(String[] args){
        Client client = new Client("stefans");
        client.run();
    }
}
