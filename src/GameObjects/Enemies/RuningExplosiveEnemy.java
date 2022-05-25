package GameObjects.Enemies;

import GameObjects.CoreGameObjects.ID;
import Handler.Handler;

public class RuningExplosiveEnemy extends ExplosiveEnemy{

    protected int acceleration;
    public RuningExplosiveEnemy(int x, int y, int width, int height, ID id, Handler handler, int movingrange, int damage, int explosionRange, int acceleration) {
        super(x, y, width, height, id, handler, movingrange, damage, explosionRange);
        this.acceleration = acceleration;
    }

    @Override
    public void moving(){
        if(velX == 0){
            setVelX(-1);
        }

        if(player != null  &&player.getX() - x > 0 && isActivated ){
            velX = acceleration;
        }
        else if(player != null && isActivated ){
            velX = -acceleration;
        }
        else if(Math.abs(x -startX) >= movingrange ){
            setVelX(-1 * velX);
        }
    }
}
