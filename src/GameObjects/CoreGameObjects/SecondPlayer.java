package GameObjects.CoreGameObjects;

import Game.Game;
import Handler.Handler;

public class SecondPlayer extends Player{
    /**
     * Sets the x and y coordinates, game object handler and game instance
     *
     * @param x       x coordinate
     * @param y       y coordinate
     * @param width   width of player
     * @param height  height of player
     * @param id      id - player
     * @param handler game object handler
     * @param game    instance of game class
     */
    public SecondPlayer(int x, int y, int width, int height, ID id, Handler handler, Game game) {
        super(x, y, width, height, id, handler, game);
    }

    @Override
    public void tick(){
        x = game.client.secondPlayerX;
        y = game.client.secondPlayerY;
        velX = game.client.secondPlayerVelX;
        velY = game.client.secondPlayerVelY;
    }
}
