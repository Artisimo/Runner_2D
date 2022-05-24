import java.awt.*;

public class Projectile extends GameObject{
    private int side;
    protected Handler handler;

    public Projectile(int x, int y, int width, int height, ID id,Handler handler, int damage,int side) {
        super(x, y, width, height, id);
        this.side = side;
        this.damage = damage;
        this.handler = handler;

    }

    @Override
    public void tick(){
        setX(getX() + 5 * side);
        collision();
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }

    public void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject temp = handler.object.get(i);
            if(temp.getId() == ID.Platform && temp.getBounds().intersects(this.getBounds())){
                handler.removeObject(this);
            }
        }
    }

}
