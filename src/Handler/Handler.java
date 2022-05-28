package Handler;

import GameObjects.CoreGameObjects.ID;
import GameObjects.GameObject;
import GameObjects.CoreGameObjects.Hpbar;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    GameObject hpbar;

    public void tick(){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            if(tempObject.getId() != ID.Hpbar){
                tempObject.render(g);
            }else{
                hpbar = tempObject;
            }
        }
        hpbar.render(g);
    }

    public void addObject(GameObject object){

        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }
}
