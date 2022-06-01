package Enviroment;

import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window {

    class WindowEventHandler extends WindowAdapter {
        public void windowClosing(WindowEvent evt) {
            mySqlDatabase.closeConn();

        }
    }
    /**
     * Creates and sets up the window for the game.
     * @param width width of window
     * @param height height of window
     * @param title title of the window
     * @param game Game instance to generate in the window
     */
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}

