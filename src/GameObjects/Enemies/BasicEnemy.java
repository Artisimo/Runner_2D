package GameObjects.Enemies;
import Enviroment.Animation;
import Enviroment.Texture;
import Game.Game;
import GameObjects.GameObject;
import GameObjects.CoreGameObjects.ID;
import Handler.Handler;
import java.awt.*;


public class BasicEnemy extends GameObject {

    protected int movingrange;
    protected int startX;
    protected int startY;
    protected float gravity = 0.5f;
    protected Handler handler;

    Texture tex = Game.getInstance();

    private Animation WalkRight;
    private Animation WalkLeft;

    public BasicEnemy(int x, int y, int width, int height, ID id, Handler handler, int movingrange, int damage) {
        super(x, y, width, height, id);
        this.movingrange = movingrange;
        startX = x;
        startY = y;
        this.handler = handler;
        this.damage = damage;
        WalkLeft = new Animation(4, tex.basicEnemyImages[0],tex.basicEnemyImages[1],tex.basicEnemyImages[2],tex.basicEnemyImages[3]);
        WalkRight = new Animation(4, tex.basicEnemyImages[4],tex.basicEnemyImages[5],tex.basicEnemyImages[6],tex.basicEnemyImages[7]);
    }

    @Override
    public void tick() {
        moving();
        x += velX;
        y += velY;
        falling = true;
        if(jumping || falling){

            if(velY < 10){
                velY += gravity;
            }
        }

        if(velX != 0){
            if(velX < 0){
                WalkLeft.runAnimation();
            }else if(velX > 0){
                WalkRight.runAnimation();
            }
        }
        collision();
    }

    @Override
    public void render(Graphics g) {

        if(velX != 0){
            if(velX < 0){
                WalkLeft.drawAnimation(g, x, y, 64, 128);
            }else if(velX > 0){
                WalkRight.drawAnimation(g, x, y, 64, 128);
            }
        }else{
            g.drawImage(tex.basicEnemyImages[0], x, y,64, 128, null);
        }
    }

    public void moving(){
        if(velX == 0){
            setVelX(-3);
        }

        if(Math.abs(x -startX) >= movingrange){
            setVelX(-1 * velX);
        }
    }
    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if(temp.getId() == ID.Platform){

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
            }
        }

    }

    public boolean bottomCollision(Rectangle platform){
        return platform.intersects(getBoundsBottom());
    }

    public Rectangle getBoundsBottom(){return new Rectangle(x + (width / 2) - (width/2)/2, y + (height / 2),width / 2, height / 2);}

    public Rectangle getBoundsTop(){
        return new Rectangle(x + (width / 2) - (width/2)/2, y,width/ 2, height/2);
    }
    public Rectangle getBoundsLeft(){
        return new Rectangle(x , y + height/4,width/4, height/2);
    }
    public Rectangle getBoundsRight(){
        return new Rectangle(x + width - width/4, y + height/4,width/4, height/2);
    }

}
