package MenuObjects;

import Game.Game;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class StartGameButton extends MenuObject{
    public StartGameButton(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) throws SQLException, IOException, InterruptedException {
        game.client.startGame();
    }

    @Override
    public void tick() throws SQLException {

    }

    @Override
    public void render(Graphics g) throws SQLException {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, width, height);
    }
}
