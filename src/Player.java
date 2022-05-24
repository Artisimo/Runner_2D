import java.awt.*;

public class Player extends GameObject{
    Handler handler;
    private float gravity = 0.5f;
    public int maxhp= 100;
    public int currenthp = maxhp;
    public int attacked = 0;
    private boolean leftbound = true;

    public Player(int x, int y, int width, int height, ID id, Handler handler){
        super(x, y,width, height, id);
        this.handler = handler;

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
                    //temp = null;
                } else if(this.getBounds().intersects(temp.getBounds()) ){
                    if(attacked <=0){
                        handler.hpbar.currenthp = handler.hpbar.currenthp - temp.damage;
                        attacked = 180;
                    }
                    leftbound  = false;
                }else {
                    leftbound = true;
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
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }
}
