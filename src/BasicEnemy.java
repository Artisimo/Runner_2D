import java.awt.*;

public class BasicEnemy extends GameObject{

    protected int movingrange;
    protected int startX;
    protected int startY;
    private float gravity = 0.5f;
    Handler handler;

    public BasicEnemy(int x, int y, int width, int height, ID id,Handler handler, int movingrange) {
        super(x, y, width, height, id);
        this.movingrange = movingrange;
        startX = x;
        startY = y;
        this.handler = handler;
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
        collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
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
//                if(this.getBoundsTop().intersects(temp.getBounds())){
//                    velY = 0;
//                    y = temp.getY() + temp.getHeight();
//                }

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

    public Rectangle getBoundsBottom(){return new Rectangle(x + (width / 2) - (width/2)/2, y + (height / 2),width / 2, height / 2);
    }
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
