package TTGameFiles;


import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Canvas;



public class Window extends Canvas{

    //private static final long serialVersionUID = 1246506276288631690L;

    public Window(int height, int width, String title, Game game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

// this is for the the Top right exit button to work
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// this is so it cant be resizable
        frame.setResizable(false);
//this just makes the frame open in the middle of the screen
        frame.setLocationRelativeTo(null);
        frame.add(game);

//frame visibility
        frame.setVisible(true);
        game.start();

    }

}