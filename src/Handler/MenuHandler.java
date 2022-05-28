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

    public Game game;
    public MenuHandler(Game game) {
        this.game = game;
    }
    public void generateUserNamePrompt(Graphics g){
        object.clear();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.WIDTH, Game.HEIGHT );

        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.MAGENTA);
        int textWidth = g.getFontMetrics().stringWidth("Enter your username: ");
        g.drawString("Enter your username: ", Game.WIDTH / 2 - textWidth, Game.HEIGHT / 2 - 100);

        g.setColor(Color.CYAN);
        g.drawString(game.userName, Game.WIDTH / 2 + 10, Game.HEIGHT / 2 - 100);

        object.add(new SetUserNameButton((Game.WIDTH / 2) - 100,  Game.HEIGHT / 2, MenuObjectID.userNameSetButton   ,100, 50));
        for(int i = 0; i < object.size(); i++){
            object.get(i).render(g);
        }
    }

    public void generateStartMenu(Graphics g){
        object.clear();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Game.WIDTH, Game.HEIGHT );
        g.setColor(Color.RED);

        for (int i = 0; i < Game.levelsAmount; i++) {

            object.add( new LevelSelectButton(Game.WIDTH / 2 - 100, (i * 64) + (i+1) * 30, MenuObjectID.levelButton, 100, 50, "/Levels/level" + (i+1) + ".png"));
        }

        for(int i = 0; i < object.size(); i++){
            object.get(i).render(g);
        }
    }

    public void generatePauseMenu(Graphics g){
        object.clear();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,Game.WIDTH, Game.HEIGHT );

        object.add(new ContinuePlayingButton(Game.WIDTH / 3, Game.HEIGHT / 2 - 50,MenuObjectID.continuePlayingButton, 100, 50));
        object.add(new RestartLevelButton(Game.WIDTH / 3 + 150, Game.HEIGHT / 2 - 50,MenuObjectID.restartLevelButton, 100, 50));
        object.add(new QuitButton(Game.WIDTH / 3 + 300, Game.HEIGHT / 2 - 50,MenuObjectID.quitButton, 100, 50));

        for(int i = 0; i < object.size(); i++){
            object.get(i).render(g);
        }
    }


   public void executeClick(Graphics g, MouseEvent e, Game game){
       for(int i = 0; i < object.size(); i++){
           MenuObject tempObject = object.get(i);
           if(e.getX() >= tempObject.getX() && e.getX() <= tempObject.getX() + tempObject.getWidth() && e.getY() >= tempObject.getY() && e.getY() <= tempObject.getY() + tempObject.getHeight()){
               tempObject.onClick(game);
           }
       }
   }
}
