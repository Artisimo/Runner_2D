import java.awt.*;

public class Hpbar {
    private int green = 255;
    public  int currenthp;
    public int maxhp;

    Hpbar (Player player){
        this.maxhp = player.maxhp;
        currenthp = maxhp;
    }
    public void tick(){
       currenthp =  (int) Game.clamp(currenthp, 0, maxhp);
       green = 255 * currenthp/100;
    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(160, green, 0));
        g.fillRect(15, 15, currenthp * 2, 32);
        g.setColor(Color.WHITE);
        g.drawRect(15, 15, 200, 32);

    }


}
