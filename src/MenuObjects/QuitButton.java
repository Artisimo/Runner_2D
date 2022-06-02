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
        System.out.println(game.isInMultiplayer);
        try {
            if(game.isInMultiplayer){
                System.out.println("before shutdown");
                game.client.shutdown();
                System.out.println("after shutdown");
                System.out.println("before close conn");
                mySqlDatabase.closeConn();
                System.out.println("after close conn");
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
