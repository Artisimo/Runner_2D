package Enviroment;

import GameObjects.GameObject;
import GameObjects.CoreGameObjects.ID;
import Handler.*;
import Game.*;
import MenuObjects.MenuObject;
import MenuObjects.MenuObjectID;
import MenuObjects.SetUserNameButton;
import kotlin.coroutines.CoroutineContext;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter {

    public Handler handler;
    private boolean moving = false;
    private Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    public void keyPressed (KeyEvent e) {

        int key = e.getKeyCode();

        if(game.gameState == GameState.USERNAME_PROMPT){
            if(key == KeyEvent.VK_BACK_SPACE && game.userName.length() > 0){
                game.userName = game.userName.substring(0, game.userName.length() - 1);
            }else if(key != KeyEvent.VK_SHIFT && key != KeyEvent.VK_CONTROL && key != KeyEvent.VK_ESCAPE){
                if(game.userName.length() < 25){
                    game.userName = game.userName + e.getKeyChar();
                }
            }
        }else if(game.gameState == GameState.PLAYING){
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

                    if(key == KeyEvent.VK_ESCAPE){
                        game.gameState = GameState.PAUSED;
                    }
                }
            }
        }else if(game.gameState == GameState.PAUSED){
            if(key == KeyEvent.VK_ESCAPE){
                game.gameState = GameState.PLAYING;
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
