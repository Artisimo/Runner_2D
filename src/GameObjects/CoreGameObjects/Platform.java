package GameObjects.CoreGameObjects;
import Enviroment.Texture;
import Game.Game;
import GameObjects.GameObject;
import Handler.Handler;
import java.awt.*;

public class Platform extends GameObject {

    private Handler handler;
    Texture tex = Game.getInstance();

    public Platform(int x, int y,int width, int height, ID id, Handler handler){
        super(x, y,width, height, id);
        this.handler = handler;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(x , y ,width, height);

        //g.drawImage(tex.platformImages[0], x, y, null);

    }

}
