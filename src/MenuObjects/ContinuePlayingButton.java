package MenuObjects;

import Game.*;

import java.awt.*;

public class ContinuePlayingButton extends MenuObject{


    public ContinuePlayingButton(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) {
        game.gameState = GameState.PLAYING;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(x, y, width, height);
    }
}
