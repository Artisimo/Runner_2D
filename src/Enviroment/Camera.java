package Enviroment;
import Game.Game;
import GameObjects.GameObject;

public class Camera {

    private float x, y;
    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject player, int levelWidth, int levelHeight){
        x = Game.clamp(-player.getX() + Game.WIDTH/2 + player.getWidth(), levelWidth + Game.WIDTH - 20, 0);
        y = Game.clamp(-player.getY() + Game.HEIGHT/ 2, levelHeight + Game.HEIGHT - 40,0);
        //y = -player.getY() + Game.Game.HEIGHT/ 2;
        //System.out.println(levelHeight + Game.Game.HEIGHT);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
