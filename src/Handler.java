import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    Hpbar hpbar;

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
            hpbar.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
            hpbar.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }
    public void addHpbar(Hpbar hpbar){this.hpbar = hpbar; }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
