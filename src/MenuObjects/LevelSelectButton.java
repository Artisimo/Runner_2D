package MenuObjects;
import Game.*;
import GameObjects.CoreGameObjects.ID;
import Enviroment.mySqlDatabase;

import java.awt.*;
import java.sql.SQLException;

public class LevelSelectButton extends MenuObject{

    private String levelName;
    private Game game;

    private String bestScorePlayer;

    private String bestScoreEver;

    public LevelSelectButton(int x, int y, MenuObjectID id, int width, int height, String levelName, Game game) {
        super(x, y, id, width, height);
        this.levelName = levelName;
        this.game = game;
    }

    @Override
    public void tick() throws SQLException {
        bestScoreEver = "Best score of all time: " + mySqlDatabase.getHighestScoreOfAllTime(levelName.substring(8, 14));
        bestScorePlayer = "Your best score: " + mySqlDatabase.getUsersHighestScoreOfAllTime(levelName.substring(8, 14), Integer.toString(game.userId));
    }

    @Override
    public void onClick(Game game) {
        if(game.isInMultiplayer){
            game.gameState = GameState.LOBBY;
        }else{
            game.play(levelName);
        }
    }

    public void render(Graphics g) throws SQLException {
        g.setColor(Color.CYAN);
        int textWidth = g.getFontMetrics().stringWidth(levelName.substring(8, 14));
        g.drawRect(x, y,width, height);
        g.setColor(Color.WHITE);
        g.drawString(levelName.substring(8, 14), x + (width - textWidth) /2 , y + height / 2);

        textWidth = g.getFontMetrics().stringWidth(bestScorePlayer);
        g.drawString(bestScorePlayer, x - textWidth - 20, y + height/ 2);

        g.drawString(bestScoreEver, x + width +  20, y + height/ 2);
    }
}
