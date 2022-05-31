package MenuObjects;

import Enviroment.Texture;
import Game.*;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class BackToMenuButton extends MenuObject{

    public BackToMenuButton(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }
    private Texture tex = Game.getInstance();
    @Override
    public void onClick(Game game) throws IOException {

        game.gameState = GameState.MENU;
        game.client.shutdown();
        game.isInMultiplayer = false;
    }

    @Override
    public void tick() throws SQLException {

    }

    @Override
    public void render(Graphics g) throws SQLException {
        g.drawImage(tex.buttonImages[2], x, y, null);
    }
}
