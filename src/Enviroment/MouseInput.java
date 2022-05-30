package Enviroment;

import Game.Game;
import Handler.MenuHandler;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    Game game;
    Graphics g;

    public MouseInput(Game game, Graphics g){
        this.game = game;
        this.g = g;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        game.menuHandler.executeClick(g, e, game);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
