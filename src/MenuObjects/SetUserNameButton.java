package MenuObjects;

import Game.*;

import java.awt.*;

public class SetUserNameButton extends MenuObject{

    public SetUserNameButton(int x, int y, MenuObjectID id, int width, int height) {
        super(x, y, id, width, height);
    }

    @Override
    public void onClick(Game game) {
        if(game.userName.length() > 5 && game.userName.length() < 25 ){
            game.gameState = GameState.MENU;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.drawRect(x, y, width, height);
        int textWidth = g.getFontMetrics().stringWidth("NEXT");
        g.drawString("NEXT", x + (width -  textWidth) / 2, y + height / 2);
    }
}
