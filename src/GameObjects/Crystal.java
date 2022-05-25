package GameObjects;

import Enviroment.Animation;
import Enviroment.Texture;
import Game.Game;
import Handler.Handler;
import java.awt.*;

public class Crystal extends GameObject{
    private int x, y, width, height;
    private Handler handler;

    Texture tex = Game.getInstance();

    private Animation starAnimation;

    public Crystal(int x, int y, int width, int height, ID id, Handler handler) {
        super(x, y, width, height, id);
        this.x = x;
        this.y = y;
        this.handler = handler;
        starAnimation = new Animation(5, tex.crystalImages[0], tex.crystalImages[1], tex.crystalImages[2], tex.crystalImages[3]);
    }

    @Override
    public void tick() {
        starAnimation.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        starAnimation.drawAnimation(g, x, y);
    }
}
