package MenuObjects;

import Enviroment.Texture;
import Game.*;

import java.awt.*;

public class SetUserNameButton extends MenuObject{

    Texture tex = Game.getInstance();
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
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.buttonImages[0], x, y, null);
    }
}
