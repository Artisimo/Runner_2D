package MenuObjects;

import Game.*;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class ChangeGameStateButton extends MenuObject{

    private GameState changeToGameState;

    public ChangeGameStateButton(int x, int y, MenuObjectID id, int width, int height, GameState gameState) {
        super(x, y, id, width, height);
        this.changeToGameState = gameState;
    }

    @Override
    public void onClick(Game game) throws SQLException, IOException {
        game.gameState = changeToGameState;
        game.isMenuGenerated = false;
        //game.client.shutdown();
    }

    @Override
    public void tick() throws SQLException {

    }

    @Override
    public void render(Graphics g) throws SQLException {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, width, height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        int textWidth = g.getFontMetrics().stringWidth("BACK");
        g.drawString("BACK", x + (width - textWidth) /2, y + 35);
    }
}
