package GameObjects.PowerUps;

import GameObjects.CoreGameObjects.ID;
import GameObjects.GameObject;

import java.awt.*;

public class SpeedPowerUp extends GameObject {
    public SpeedPowerUp(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, width, height);
    }
}
