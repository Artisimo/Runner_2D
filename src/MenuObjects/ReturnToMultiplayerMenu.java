package MenuObjects;

import Game.*;

import java.awt.*;
import java.sql.SQLException;

public class ReturnToMultiplayerMenu extends MenuObject{

    public ReturnToMultiplayerMenu(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) throws SQLException {
        game.gameState = GameState.MULTIPLAYER_MENU;
        game.isMultiiplayerMenuGenerated = false;
        game.levelSelectMenuGenerated = false;
    }

    @Override
    public void tick() throws SQLException {

    }

    @Override
    public void render(Graphics g) throws SQLException {
        g.setColor(Color.YELLOW);
        g.drawRect(x, y, width, height);
    }
}
