package MenuObjects;

import Game.Game;
import Game.GameState;
import Multiplayer.Client;

import java.awt.*;
import java.sql.SQLException;

public class Button extends MenuObject{
    public Button(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) {
        game.gameState = GameState.MULTIPLAYER_MENU;
        game.client = new Client(game.userName);
        game.client.run();
    }

    @Override
    public void tick() throws SQLException {
    }

    @Override
    public void render(Graphics g) throws SQLException {
        g.setColor(Color.RED);
        g.drawRect(x, y, width, height);
    }
}
