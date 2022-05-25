package GameObjects;
import Enviroment.Animation;
import Enviroment.Texture;
import Handler.Handler;
import java.awt.*;
import Game.*;

public class Player extends GameObject{
    Handler handler;
    private float gravity = 0.5f;
    public int maxhp= 100;
    public int currenthp = maxhp;
    public int attacked = 0;

    Texture tex = Game.getInstance();

    private Animation playerWalk;
    private boolean leftbound = true;
    private GameObject intersactedEnemy;

    public Player(int x, int y, int width, int height, ID id, Handler handler){
        super(x, y,width, height, id);
        this.handler = handler;

        playerWalk = new Animation(5, tex.playerImages[1], tex.playerImages[2], tex.playerImages[3]);

    }
    @Override
    public void tick() {
        x += velX;
        y += velY;
        falling = true;
        if(jumping || falling){

            if(velY < 10){
                velY += gravity;
            }
        }
        if (attacked > 0){
            attacked  = attacked-1;
        }
        collision();

        playerWalk.runAnimation();
        //System.out.println(currenthp);
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
                        currenthp -= temp.damage;
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
                    currenthp -= temp.damage;
                    //System.out.println(temp.damage);
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
//            g.setColor(Color.YELLOW);
//            g.fillRect(x, y, width, height);
        }

//
//        g.setColor(Color.blue);
//        g.drawRect(x , y + 5,10, height-10);
    }

    public int getCurrenthp() {
        return currenthp;
    }

    public void setCurrenthp(int currenthp) {
        this.currenthp = currenthp;
    }
}
