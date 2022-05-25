package GameObjects.PowerUps;

import GameObjects.GameObject;
import GameObjects.CoreGameObjects.ID;
import Handler.Handler;

import java.awt.*;

public class HealPowerUp extends GameObject {

    private Handler handler;

    public HealPowerUp(int x, int y, int width, int height, ID id, Handler handler) {
        super(x,y,width,height,id);
        this.handler = handler;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
    }
}
