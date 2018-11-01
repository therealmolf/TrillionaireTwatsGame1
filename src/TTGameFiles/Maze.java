/*
    This is the Front-end of the game
*/

import ttc.game.*;
import java.awt.*;
import java.awt.geom.*;
public class Maze implements SolidObject {
    
    private int x;
    private int y;
    private int w;
    private int h;

    public Maze(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    @Override
    public void drawObject(Graphics2D g2d) {
        Rectangle2D.Double maz = new Rectangle2D.Double(x, y, w, h);
        g2d.fill(maz);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Rectangle getBorder() {
        return (new Rectangle(x, y, w, h));
    }
    
}
