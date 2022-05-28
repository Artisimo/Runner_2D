package MenuObjects;

import Game.*;

import java.awt.*;

public class RestartLevelButton extends MenuObject{
    public RestartLevelButton(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) {
        game.play("/Levels/" + game.levelname + ".png");
        //game.gameState = GameState.PLAYING;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.drawRect(x, y, width, height);
    }
}
