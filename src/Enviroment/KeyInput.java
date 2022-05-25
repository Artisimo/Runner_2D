package Enviroment;

import GameObjects.GameObject;
import GameObjects.ID;
import Handler.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter {

    public Handler handler;

    public KeyInput(Handler handler) {

        this.handler = handler;
    }

    public void keyPressed (KeyEvent e) {

        int key = e.getKeyCode();
        for(int i = 0;i < handler.object.size(); i++) {

            GameObject temp = handler.object.get(i);

            if(temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_D) {
                    temp.setVelX(5);
                }

                if (key == KeyEvent.VK_A) {
                    temp.setVelX(-5);
                }
                if(key == KeyEvent.VK_SPACE && ! temp.isJumping() && ! temp.isFalling()){
                    temp.setVelY(-15);
                    temp.setJumping(true);
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        for(int i = 0;i < handler.object.size(); i++) {

            GameObject temp = handler.object.get(i);

            if(temp.getId() == ID.Player){
                if(key == KeyEvent.VK_D){
                    temp.setVelX(0);
                }

                if (key == KeyEvent.VK_A) {
                    temp.setVelX(0);
                }
            }
        }
    }














}
