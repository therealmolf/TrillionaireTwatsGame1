/* 
    Game Background
*/

import ttc.game.*;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.ImageIcon;

public class Bg {
        
    private Image bg;
    private double x, y;
    
    public Bg(double x, double y){
        this.x = x;
        this.y = y;
        loadPic();
    }
    
    public void loadPic(){
        bg = new ImageIcon(getClass().getResource("bg-04.jpg")).getImage();
        }
    
    public void drawBackground(Graphics g2d, AffineTransform reset){
        g2d.drawImage(bg, 0, 0, null);
        }
}
