import java.awt.*;

public class Player extends GameObject{


    Handler handler;

    public Player(int x, int y, int width, int height, ID id, Handler handler){
        super(x, y,width, height, id);
        this.handler = handler;

    }
    @Override
    public void tick() {
        x += velX;
        y += velY;
        System.out.println(falling);

        if(jumping || falling){

            if(velY < 10){
                velY += 1;
            }
        }
        collisions();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }


    public void collisions(){

        for(int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if(temp.getId() == ID.Platform){
                if(this.getBounds().intersects(temp.getBounds())){
                    if(velY > 0){
                        falling = false;
                        jumping = false;
                        velY = 0;
                        this.setY(temp.getY()-height + 1);
                    }
                    else if(velY < 0){
                        velY = 0;
                        this.setY(temp.getY()+temp.getHeight());
                        falling = true;
                        jumping = false;
                    }
                    else{
                        jumping = false;
                        falling = false;
                    }
                }
                else {
                    falling = true;
                }
            }
        }


    }


}
