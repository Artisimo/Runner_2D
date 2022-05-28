package MenuObjects;

import Enviroment.Texture;
import Game.*;

import java.awt.*;

public class ContinuePlayingButton extends MenuObject{
    Texture tex = Game.getInstance();

    public ContinuePlayingButton(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) {
        game.gameState = GameState.PLAYING;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.buttonImages[3], x, y, null);
    }
}
