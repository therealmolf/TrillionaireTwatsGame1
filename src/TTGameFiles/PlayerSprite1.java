
// This is the player class that holds all the attributes of a player avatar

import ttc.game.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

// first class for creating the avatar
public class PlayerSprite1 /*implements SolidObject*/ {

    /*
    private Image avatar;
    private Image avatar1;
    private Image avatar2;
    */

    private int x,y;
    private int playerID;

    //private ImageIcon image, image2;

    private int points;


    public PlayerSprite1(int x, int y){
        this.x = x;
        this.y = y;
        points = 0;

        /*
        try {
            loadPic();
        } catch (IOException ex) {
            Logger.getLogger(PlayerSprite1.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
        /*

    public void loadPic() throws IOException{

            avatar1 = new ImageIcon(getClass().getResource("Avatars-02.png")).getImage();
            avatar2 = new ImageIcon(getClass().getResource("Avatars-03.png")).getImage();

        }

        */

    public void drawObject(Graphics g2d, AffineTransform reset, int id){
        playerID = id;
        if(playerID == 1){
            /*
            avatar = avatar1;
            g2d.drawImage(avatar, x, y, null);
*/
        }

        if(playerID == 2){
            /*
            avatar = avatar2;
            g2d.drawImage(avatar, x, y, null);
*/
        }
    }

    /*
    public void moveH(double n) {
        x += n;
    }

    public void moveV(double n) {
        y += n;
    }

    public void setX(double n) {
        x = (int) n;
    }

    public void setY(double n) {
        y = (int) n;
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
        int tempX = (int) x;
        int tempY = (int) y;
        return (new Rectangle(tempX, tempY, 107, 113));
    }
    public int getPoints(){
        return points;

    }
    public void addPoint(){
        points += 1;
    }

*/

}
