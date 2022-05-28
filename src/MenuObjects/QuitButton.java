package MenuObjects;

import Game.*;

import java.awt.*;

public class QuitButton extends MenuObject{
    public QuitButton(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) {
        System.exit(1);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.drawRect(x, y, width, height);
    }
}
