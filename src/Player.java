import java.awt.*;

public class Player extends GameObject{
    Handler handler;
    private float gravity = 0.5f;

    public Player(int x, int y, int width, int height, ID id, Handler handler){
        super(x, y,width, height, id);
        this.handler = handler;

    }
    @Override
    public void tick() {
        x += velX;
        y += velY;
        System.out.println(falling);
        falling = true;
        if(jumping || falling){

            if(velY < 10){
                velY += gravity;
            }
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
                    x = temp.getX() + width;
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
