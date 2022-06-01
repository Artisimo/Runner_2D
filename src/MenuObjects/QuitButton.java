package MenuObjects;

import Enviroment.*;
import Game.*;

import java.awt.*;
import java.io.IOException;

public class QuitButton extends MenuObject{
    private Texture tex = Game.getInstance();
    public QuitButton(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) {
        try {
            if(game.isInMultiplayer){
                game.client.shutdown();
                mySqlDatabase.closeConn();
            }else{
                mySqlDatabase.closeConn();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.exit(1);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
       g.drawImage(tex.buttonImages[1], x, y, null);
    }
}
