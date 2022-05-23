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

//        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.RED);
//        g2d.draw(getBoundsBottom());
//
//        g2d.draw(getBoundsTop());
//        g.setColor(Color.CYAN);
//        g2d.draw(getBoundsRight());
//        g2d.draw(getBoundsLeft());
    }


//    public void collisions(){
//
//        for(int i = 0; i < handler.object.size(); i++){
//            GameObject temp = handler.object.get(i);
//            if(temp.getId() == ID.Platform){
//                if(this.getBounds().intersects(temp.getBounds())){
//                        if (temp.getX() - this.getX() >= 0){
//                            if (temp.getY() - this.getY() >= 0){
//                                if(temp.getX() - this.getX() <=   temp.getY() - this.getY() ){
//                                    velY = 0;
//                                    jumping = false;
//                                    falling = false;
//                                    setY(temp.getY() - height + 1);
//                                }
//                                else {
//                                    velX = 0;
//                                    setX(temp.getX() - width - 1);
//                                }
//                            }
//                            else{
//                                if(temp.getX() - this.getX() >= temp.getY() - this.getY() ){
//                                    velY = 0;
//                                    jumping = false;
//                                    falling = true;
//                                    setY(temp.getY() + temp.getHeight());
//                                }
//                                else {
//                                    velX = 0;
//                                    setX(temp.getX() - width - 1);
//                                }
//                            }
//                        }
//                        else {
//                            if (temp.getY() - this.getY() >= 0){
//                                if(temp.getX() - this.getX() <= temp.getY() - this.getY() ){
//                                    velY = 0;
//                                    jumping = false;
//                                    falling = false;
//                                    setY(temp.getY() - height + 1);
//                                }
//                                else {
//                                    velX = 0;
//                                    setX(temp.getX() + temp.width + 1);
//                                }
//                            }
//                            else{
//                                if(temp.getX() - this.getX() >= temp.getY() - this.getY() ){
//                                    velY = 0;
//                                    jumping = false;
//                                    falling = true;
//                                    setY(temp.getY() + temp.getHeight());
//                                }
//                                else {
//                                    velX = 0;
//                                    setX(temp.getX() + temp.width + 1);
//                                }
//                            }
//                        }
//                    if(Math.abs(temp.getX() - this.getX()) > Math.abs(temp.getY() - this.getY())){
//                        if(temp.getY() >= this.getY()){
//                            velY = 0;
//                            falling =false;
//                            jumping = false;
//                            setY(temp.getY() - height +1);
//                        }
//                        else if(temp.getY() < this.getY()){
//                            velY = 0;
//                            falling = true;
//                            jumping = false;
//                            setY(temp.getY() + temp.height -1 );
//                        }
//                    }
//                    else{
//                        if(temp.getX() > this.getX()){
//                            velX = 0;
//                            setX(temp.getX() - width - 1);
//                        }
//                        else if(temp.getX() < this.getX()){
//                            velX = 0;
//                            setX(temp.getX() + temp.width + 1);
//                        }
//
//                    }
//                    if(velY > 0){
//                        falling = false;
//                        jumping = false;
//                        velY = 0;
//                        this.setY(temp.getY()-height + 1);
//                    }
//                    else if(velY < 0){
//                        velY = 0;
//                        this.setY(temp.getY()+temp.getHeight());
//                        falling = true;
//                        jumping = false;
//                    }
//                    else{
//                        jumping = false;
//                        falling = false;
//                    }
//                }
//                else {
//                    falling = true;
//                }
//            }
//        }
//
//
//    }


}
