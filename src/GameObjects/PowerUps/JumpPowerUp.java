package GameObjects.PowerUps;

import GameObjects.CoreGameObjects.ID;
import GameObjects.GameObject;

import java.awt.*;

public class JumpPowerUp extends GameObject {
    public JumpPowerUp(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
}
