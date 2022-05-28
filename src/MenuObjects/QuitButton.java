package MenuObjects;

import Enviroment.Texture;
import Game.*;

import java.awt.*;

public class QuitButton extends MenuObject{
    Texture tex = Game.getInstance();
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
       g.drawImage(tex.buttonImages[1], x, y, null);
    }
}
