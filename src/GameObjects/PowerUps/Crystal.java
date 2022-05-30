package GameObjects.PowerUps;

import Enviroment.Animation;
import Enviroment.Texture;
import Game.Game;
import GameObjects.GameObject;
import GameObjects.CoreGameObjects.ID;
import Handler.Handler;
import java.awt.*;

public class Crystal extends GameObject {
    private Handler handler;

    private Texture tex = Game.getInstance();

    private Animation starAnimation;

    public Crystal(int x, int y, int width, int height, ID id, Handler handler) {
        super(x, y, width, height, id);
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
