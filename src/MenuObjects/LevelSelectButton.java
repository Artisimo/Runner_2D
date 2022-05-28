package MenuObjects;
import Game.*;
import GameObjects.CoreGameObjects.ID;

import java.awt.*;

public class LevelSelectButton extends MenuObject{

    String levelName;

    public LevelSelectButton(int x, int y, MenuObjectID id, int width, int height, String levelName) {
        super(x, y, id, width, height);
        this.levelName = levelName;
    }

    @Override
    public void onClick(Game game) {
        game.play(levelName);
    }

    public void render(Graphics g){
        g.setColor(Color.CYAN);
        y += 50;
        int textWidth = g.getFontMetrics().stringWidth(levelName.substring(8, 14));
        g.drawRect(x, y,width, height);
        g.setColor(Color.WHITE);
        String levelNum;
        g.drawString(levelName.substring(8, 14), x + (width - textWidth) /2 , y + height / 2);
    }



}
