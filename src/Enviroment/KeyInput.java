package Enviroment;

import GameObjects.GameObject;
import GameObjects.CoreGameObjects.ID;
import Handler.Handler;
import Game.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter {

    public Handler handler;
    private boolean moving = false;

    public KeyInput(Handler handler) {

        this.handler = handler;
    }

    public void keyPressed (KeyEvent e) {

        int key = e.getKeyCode();
        for(int i = 0;i < handler.object.size(); i++) {

            GameObject temp = handler.object.get(i);

            if(temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_D) {
                    temp.setVelX(temp.getSpeed());
//                    if(!moving && !temp.isFalling() && !temp.isJumping()){
//                        Game.movingRight.playMove();
//                        moving = true;
//                    }else if((temp.isFalling() || temp.isJumping()) && Game.movingRight.getClip() != null){
//                        Game.movingRight.stop();
//                    }
                }

                if (key == KeyEvent.VK_A) {
                    temp.setVelX(-1 * temp.getSpeed());
//                    if(!moving && !temp.isFalling() && !temp.isJumping()){
//                        Game.movingLeft.playMove();
//                        moving = true;
//                    }else if((temp.isFalling() || temp.isJumping()) && Game.movingLeft.getClip() != null){
//                        Game.movingLeft.stop();
//                    }
                }
                if(key == KeyEvent.VK_SPACE && ! temp.isJumping() && ! temp.isFalling()){
                    temp.setVelY(-1* temp.getJumpHeight());
                    Game.sound.setFile(0);
                    Game.sound.play();
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
//                    if(Game.movingRight.clip != null){
//                        Game.movingRight.stop();
//                    }
                    moving = false;
                }

                if (key == KeyEvent.VK_A) {
                    temp.setVelX(0);
//                    if(Game.movingLeft.clip != null){
//                        Game.movingLeft.stop();
//                    }
                    moving = false;
                }
            }
        }
    }














}
