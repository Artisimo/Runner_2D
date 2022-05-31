package MenuObjects;

import Game.*;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class LobbySelectButton extends MenuObject{

    private int lobbyID;

    public LobbySelectButton(int x, int y, MenuObjectID id, int width, int height, int lobbyID) {
        super(x, y, id, width, height);
        this.lobbyID = lobbyID;
    }

    @Override
    public void onClick(Game game) throws SQLException, IOException, InterruptedException {
        game.client.joinLobby(lobbyID);

        Thread.sleep(1000);
        game.levelSelectMenuGenerated = false;
        game.lobbyInfoGenerated = false;
        game.gameState = GameState.LOBBY;
        game.lobbyID = lobbyID;
        System.out.println(lobbyID);

    }

    @Override
    public void tick() throws SQLException {

    }

    @Override
    public void render(Graphics g) throws SQLException {
        g.setColor(Color.YELLOW);
        g.drawRect(x, y, 100, 50);
    }
}
