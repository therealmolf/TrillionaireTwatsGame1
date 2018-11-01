/* 
    Package to add repetitive processes 
*/

import ttc.game.*;
import java.awt.*;
import java.awt.geom.*;

//used as interface for all drawn objects
//useful in collision detection

public interface SolidObject {
    public void drawObject(Graphics2D g2d);
    
    public int getX();
    
    public int getY();
    
    public Rectangle getBorder();
}
