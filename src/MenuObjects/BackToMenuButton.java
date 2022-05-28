package MenuObjects;

import Game.*;

import java.awt.*;
import java.sql.SQLException;

public class BackToMenuButton extends MenuObject{

    public BackToMenuButton(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) {
        game.gameState = GameState.MENU;
    }

    @Override
    public void tick() throws SQLException {

    }

    @Override
    public void render(Graphics g) throws SQLException {
        g.setColor(Color.MAGENTA);
        g.drawRect(x, y, width, height);
    }
}
