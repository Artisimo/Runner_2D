import java.awt.*;

public class Hpbar extends GameObject{
    private int green = 255;
    public  int currenthp;
    public int maxhp;
    Player player;

    Hpbar (int x,int y,int width,int height,ID id,Player player){
        super(x,y,width,height,id);
        this.player =  player;
        this.maxhp = player.maxhp;
        currenthp = maxhp;
    }
    public void tick(){
        x = player.getX() - Game.WIDTH/2 + player.getWidth() + 15;
        y = player.getY() - Game.HEIGHT/2 + 15;
       currenthp =  (int) Game.clamp(currenthp, 0, maxhp);
       green = 255 * currenthp/100;

    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(x, y, maxhp * 2, 32);
        g.setColor(new Color(160, green, 0));
        g.fillRect(x, y, currenthp * 2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, maxhp * 2, 32);

    }


}
