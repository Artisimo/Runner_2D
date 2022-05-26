package GameObjects.CoreGameObjects;

import Enviroment.Texture;
import Game.Game;
import GameObjects.GameObject;
import Handler.Handler;

import java.awt.*;

public class FinishLine extends GameObject {

    private int x, y, width, height;
    private Handler handler;

    Texture tex = Game.getInstance();

    public FinishLine(int x, int y, int width, int height, ID id,  Handler handler) {
        super(x, y, width, height, id);
        this.x = x;
        this.y = y;
        this.handler = handler;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.finishLine, x, y,64, 128, null);
    }
}
