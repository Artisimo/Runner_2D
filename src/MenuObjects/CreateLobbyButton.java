package MenuObjects;

import Game.Game;

import java.awt.*;
import java.sql.SQLException;

public class CreateLobbyButton extends MenuObject{
    public CreateLobbyButton(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) {
        game.client.createLobby();
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
