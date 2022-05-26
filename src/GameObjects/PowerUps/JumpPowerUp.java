package GameObjects.PowerUps;

import Enviroment.Texture;
import Game.Game;
import GameObjects.CoreGameObjects.ID;
import GameObjects.GameObject;

import java.awt.*;

public class JumpPowerUp extends GameObject {
    Texture tex = Game.getInstance();
    public JumpPowerUp(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.powerUpImages[3], x, y, null);
//        g.setColor(Color.blue);
//        g.fillRect(x, y, width, height);
    }
}
