import java.awt.*;

public class Platform extends GameObject{

    private Handler handler;

    public Platform(int x, int y,int width, int height, ID id, Handler handler){
        super(x, y,width, height, id);
        this.handler = handler;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(x , y ,width, height);

    }

}
