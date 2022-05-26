package GameObjects.PowerUps;

import Enviroment.Texture;
import Game.Game;
import GameObjects.CoreGameObjects.ID;
import GameObjects.GameObject;

import java.awt.*;

public class SpeedPowerUp extends GameObject {

    Texture tex = Game.getInstance();
    public SpeedPowerUp(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.powerUpImages[2],x,y,null );
    }
}
