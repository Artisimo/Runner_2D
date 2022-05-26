package GameObjects.Enemies;
import Enviroment.Texture;
import Game.Game;
import GameObjects.GameObject;
import GameObjects.CoreGameObjects.ID;
import Handler.Handler;
import java.awt.*;

public class Projectile extends GameObject {
    private int side;
    protected Handler handler;
    Texture tex = Game.getInstance();

    public Projectile(int x, int y, int width, int height, ID id, Handler handler, int damage, int side) {
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
        g.drawImage(tex.projectile, x, y, 32, 32,null);
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
