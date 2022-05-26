package GameObjects.CoreGameObjects;
import Enviroment.Animation;
import Enviroment.Texture;
import GameObjects.GameObject;
import Handler.Handler;
import java.awt.*;
import Game.*;
import GameObjects.*;

public class Player extends GameObject {
    Handler handler;
    private float gravity = 0.5f;
    public int maxhp= 100;
    public int currenthp = maxhp;
    public int attacked = 0;
    private boolean leftbound = true;
    private GameObject intersactedEnemy;

    private int crystalsCollected = 0;
    private int speedTimer = -1;
    private int jumpTimer = -1;

    Texture tex = Game.getInstance();

    private Animation playerWalk;
    long startTime, elapsedTime;

    public Player(int x, int y, int width, int height, ID id, Handler handler){
        super(x, y,width, height, id);
        this.handler = handler;
        speed = 5;
        jumpHeight = 15;
        playerWalk = new Animation(5, tex.playerImages[1], tex.playerImages[2], tex.playerImages[3], tex.playerImages[4], tex.playerImages[5], tex.playerImages[6], tex.playerImages[7], tex.playerImages[8]);
        startTime = System.currentTimeMillis();
    }
    @Override
    public void tick() {
        x += velX;
        y += velY;
        falling = true;
        if(speedTimer < 0){
            speed = 5;
        }
        else if(speedTimer > -2){
            speedTimer -= 1;
        }
        if(jumpTimer < 0){
            jumpHeight = 15;
        }
        else if(jumpTimer > -2){
            jumpTimer -= 1;
        }
        if(jumping || falling){

            if(velY < 10){
                velY += gravity;
            }
        }
        if (attacked > 0){
            attacked  = attacked-1;
        }
        if(exploded > 0){
            explosion();
        }
        collision();
        playerWalk.runAnimation();
        //System.out.println(x + " " + y);
    }

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
                }

                if(this.getBoundsLeft().intersects(temp.getBounds())){
                    x = temp.getX() + temp.getWidth();
                }
            }
            else if(temp.getId() == ID.BasicEnemy ){
                if(this.bottomCollision(temp.getBounds()) && attacked <=0 && leftbound){
                    handler.removeObject(temp);
                    temp = null;
                } else if(this.getBounds().intersects(temp.getBounds()) ){
                    if(attacked <=0){
                        currenthp -= temp.getDamage();
                        attacked = 180;
                    }
                    leftbound  = false;
                    intersactedEnemy = temp;
                }else if (intersactedEnemy == temp){
                    leftbound = true;
                }

            }
            else if(temp.getId() == ID.Projectile){
                if(this.getBounds().intersects(temp.getBounds())){
                    currenthp -= temp.getDamage();
                    handler.removeObject(temp);
                }
            }
            else if (temp.getId() == ID.ExplosiveEnemy){
                if(temp.getExploded() > 0){
                    currenthp -= temp.getDamage();
                }
            }else if(temp.getId() == ID.FinishLine){
                if(getBounds().intersects(temp.getBounds())){
                    elapsedTime = System.currentTimeMillis() - startTime;
                    long seconds = elapsedTime / 1000;
                    long miliSeconds = elapsedTime % 1000;
                    System.out.println(elapsedTime);
                    System.out.println("Level finished in " + seconds + "." + miliSeconds + " seconds, " + crystalsCollected + " / 3 crystals collected. SEND TO DB, save the result");
                    System.exit(1);
                }
            }else if(temp.getId() == ID.Crystal){
                if(getBounds().intersects(temp.getBounds())){
                    handler.removeObject(temp);
                    crystalsCollected++;
                }
            }else if(temp.getId() == ID.HealPowerUp){
                if(getBounds().intersects(temp.getBounds())){
                    currenthp = Game.clamp(currenthp+25, 0, maxhp);
                    handler.removeObject(temp);
                }
            }else if(temp.getId() == ID.MaxHpPowerUp){
                if(getBounds().intersects(temp.getBounds())){
                    maxhp += 50;
                    currenthp = Game.clamp((int)(currenthp * ((float)maxhp/(float)(maxhp-50))),0,maxhp);
                    handler.removeObject(temp);
                }
            }else  if(temp.getId() == ID.SpeedPowerUp){
                if(getBounds().intersects(temp.getBounds())){
                    speed *= 2;
                    speedTimer = 300;
                    handler.removeObject(temp);
                }
            }else if(temp.getId() == ID.JumpPowerUp){
                if(getBounds().intersects(temp.getBounds())){
                    jumpHeight *= 2;
                    jumpTimer = 180;
                    handler.removeObject(temp);
                }
            }
        }

    }
    public boolean bottomCollision(Rectangle platform){
        return platform.intersects(getBoundsBottom());
    }

    public Rectangle getBoundsBottom(){
        return new Rectangle(x + (width / 2) - (width/2)/2, y + (height / 2),width / 2, height / 2);
    }
    public Rectangle getBoundsTop(){
        return new Rectangle(x + (width / 2) - (width/2)/2, y,width/ 2, height/2);
    }
    public Rectangle getBoundsLeft(){
        return new Rectangle(x , y + 5,10, height-10);
    }
    public Rectangle getBoundsRight(){
        return new Rectangle(x + width - 10, y + 5,10, height-10);
    }

    @Override
    public void render(Graphics g) {
        if(velX != 0 ){
            playerWalk.drawAnimation(g, x, y);
        }else{
            g.drawImage(tex.playerImages[0], x, y, null);
        }
    }

    public int getCurrenthp() {
        return currenthp;
    }

    public void setCurrenthp(int currenthp) {
        this.currenthp = currenthp;
    }

    public void explosion(){
        currenthp = currenthp - exploded;
        exploded = 0;
    }
}
