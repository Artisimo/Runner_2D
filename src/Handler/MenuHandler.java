package Handler;

import GameObjects.GameObject;
import Enviroment.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Stream;
import Game.*;
import MenuObjects.*;

public class MenuHandler {

    public LinkedList<MenuObject> object = new LinkedList<MenuObject>();

    public void generateStartMenu(Graphics g){
        object.clear();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.WIDTH, Game.HEIGHT );
        g.setColor(Color.RED);

        for (int i = 0; i < Game.levelsAmount; i++) {

            object.add( new LevelSelectButton((i * 100) + (i+1) * 64, 100, MenuObjectID.levelButton, 100, 100, "/Levels/level" + (i+1) + ".png"));
        }

        for(int i = 0; i < object.size(); i++){
            object.get(i).render(g);
        }
    }

   public void executeClick(Graphics g, MouseEvent e, Game game){
       for(int i = 0; i < object.size(); i++){
           MenuObject tempObject = object.get(i);
//           System.out.println(e.getX() + " " + e.getY());
//           System.out.println( tempObject.getX() + " " + (tempObject.getX() + tempObject.getWidth()));
           if(e.getX() >= tempObject.getX() && e.getX() <= tempObject.getX() + tempObject.getWidth() && e.getY() >= tempObject.getY() && e.getY() <= tempObject.getY() + tempObject.getHeight()){
               //System.out.println("here");
               tempObject.onClick(game);
           }
       }
   }


}
