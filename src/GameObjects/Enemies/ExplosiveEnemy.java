package GameObjects.Enemies;

import Enviroment.Animation;
import Enviroment.Texture;
import Game.Game;
import GameObjects.GameObject;
import GameObjects.CoreGameObjects.ID;
import Handler.Handler;

import java.awt.*;

public class ExplosiveEnemy extends BasicEnemy{
    protected int explosionRange;
    protected boolean isActivated = false;
    protected int timer;
    GameObject player;

    Texture tex = Game.getInstance();
    Animation explosion;

    public ExplosiveEnemy(int x, int y, int width, int height, ID id, Handler handler, int movingrange, int damage, int explosionRange) {
        super(x, y, width, height, id, handler, movingrange, damage);
        this.explosionRange = explosionRange;
        explosion = new Animation(3, tex.explosiveEnemyImages[0],tex.explosiveEnemyImages[2]);
    }

    @Override
    public void tick(){
        super.tick();
        if(isActivated && timer >= -1){
            timer -= 1;
            explosion.runAnimation();
        }
    }

    @Override
    public void render(Graphics g){
        if(!isActivated){
            if(velX > 0){
                g.drawImage(tex.explosiveEnemyImages[0], x, y, null);
            }else if(velX < 0){
                g.drawImage(tex.explosiveEnemyImages[1], x, y, null);
            }else{
                g.drawImage(tex.explosiveEnemyImages[0], x, y, null);
            }
        }else{
            explosion.drawAnimation(g, x, y);
        }

        //g.drawOval(x - explosionRange,y - explosionRange,width + 2*explosionRange,height + 2*explosionRange);
    }

    @Override
    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if(temp.getId() == ID.Platform){
                    if(this.getBoundsTop().intersects(temp.getBounds())){
                    velY = 0;
                    y = temp.getY() + temp.getHeight();
                }

                if(bottomCollision(temp.getBounds())){
                    velY = 0;
                    falling = false;
                    jumping = false;
                    y = temp.getY() - height + 1;
                }

                if(this.getBoundsRight().intersects(temp.getBounds())){
                    x = temp.getX() - width;
                    setVelX(-1 * velX);
                }

                if(this.getBoundsLeft().intersects(temp.getBounds())){
                    x = temp.getX() + temp.getWidth();
                    setVelX(-1 * velX);
                }
            }else if(temp.getId() == ID.Player){
                player = temp;
                if(temp.getBounds().intersects(new Rectangle(x - explosionRange,y - explosionRange,width + 2*explosionRange,height + 2*explosionRange))){
                    if(!isActivated){
                        isActivated = true;
                        timer = 120;
                        Game.sound.playBombTrigger();
                    }else if(timer <= 0){
                        exploded = damage;
                    }
                }
                if(isActivated && timer < 0){
                    handler.removeObject(this);
                    Game.sound.playExplosion();
                }

            }
        }
    }


}
