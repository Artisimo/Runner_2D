package MenuObjects;

import Game.Game;

import java.awt.*;
import java.sql.SQLException;

public class Label extends MenuObject{
    private String text;

    public Label(int x, int y, MenuObjectID id, int width, int height, String text) {
        super(x, y, id, width, height);
        this.text = text;
    }

    @Override
    public void onClick(Game game) {
    }

    @Override
    public void tick() throws SQLException {
    }

    @Override
    public void render(Graphics g) throws SQLException {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.MAGENTA);
        g.drawString(text, x, y);
    }
}
