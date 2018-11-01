/* 
    GUI Setup
*/


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.applet.*;
import javax.swing.ImageIcon;
import java.net.*;
import java.applet.*;

public class PlayerFrame extends JFrame {
/*
    private int width;
    private int height;
    private Container gameWindow;
    private JPanel dashboard;
    private JPanel side1;
    private JPanel center;
    private JPanel side2;
    private JTextField p1;
    private JTextField point1;
    public JButton logo;
    private JTextField p2;
    private JTextField point2;
    
    private double py1;

*/
    private int playerID;
    
    private int counter;
    File wavFile = new File("sound.WAV");
    AudioClip sound;
    
    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    
    
    
    public PlayerFrame(int w, int h){
       /*
        width = w;
        height = h;
        this.setSize(width, height);
        dashboard = new JPanel();
        side1 = new JPanel();
        center = new JPanel();
        side2 = new JPanel();
        
        Font font = new Font("Pixel", Font.BOLD,48);
        Font font1 = new Font("Pixel", Font.BOLD,18);
        Font font2 = new Font("Pixel", Font.BOLD,24);
        
        p1 = new JTextField("Player One");
        p2 = new JTextField("Player Two");
        point1 = new JTextField("Jedixuas Race");
        point2 = new JTextField("Shitakuz Race");
        
        p1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        p1.setBackground(new Color(56, 28, 100));
        p1.setForeground(Color.WHITE);
        point1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        point1.setBackground(new Color(56, 28, 100));
        point1.setForeground(Color.LIGHT_GRAY);
        point2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        point2.setHorizontalAlignment(JTextField.RIGHT);
        point2.setBackground(new Color(56, 28, 100));
        point2.setForeground(Color.LIGHT_GRAY);
        p2.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        p2.setHorizontalAlignment(JTextField.RIGHT);
        p2.setBackground(new Color(56, 28, 100));
        p2.setForeground(Color.WHITE);
        
        p1.setFont(font);
        p2.setFont(font);
        point1.setFont(font2);
        point2.setFont(font2);

        counter = 1;
        */
    }
    
    
    public void setUpGUI(){
        /* assembly of the frame's components
        this.setSize(width, height);
        this.setTitle("Player #" + playerID + " Willed Weapons");
        
        gameWindow = this.getContentPane();
        gameWindow.setPreferredSize( new Dimension (1500, 1000));
        
        GameDesign dgdd = new GameDesign(gameWindow, socket, dataOut, dataIn, playerID, py1);
        dgdd.setUpKL();
        
        //main component
        gameWindow.setLayout(new BorderLayout());
        gameWindow.add(dgdd, BorderLayout.CENTER);
        gameWindow.add(dashboard, BorderLayout.SOUTH);
        
        //bottom side of frame
        dashboard.setLayout(new GridLayout(1,3));
        dashboard.add(side1);
        dashboard.add(center);
        dashboard.add(side2);
        
        side1.setLayout(new GridLayout(2,1));
        side1.setBackground(new Color(56, 28, 100));
        side1.add(point1);
        side1.add(p1);
        
        center.setLayout(new GridLayout(2,1));
        center.setBackground(new Color(56, 28, 100));
        
        side2.setLayout(new GridLayout(2,1));
        side2.setBackground(new Color(56, 28, 100));
        side2.add(point2);
        side2.add(p2);
        */
        
        //sound try-catch
        try
        {
            sound = Applet.newAudioClip(wavFile.toURI().toURL());
        }
        catch(Exception e){e.printStackTrace();}
        
        
        
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        
        //dgdd.setUpRepaintTimer();
        //dgdd.startThreads();
        sound.play();
    }
    
    
    
   public void setUpButtons(){
        
    }
    
    public void connectToServer(){
        // networking
        try {
            socket = new Socket("localhost", 8888);
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());
            
            
            playerID = dataIn.readInt();  //FIRST THING THE SERVER SENDS
            //py1 = dataIn.readDouble();
            
            System.out.println("CONNECTED TO SERVER. >>>" + playerID);
            
            String msg = dataIn.readUTF();
            System.out.println(msg);
            
        } catch(IOException ex){
            System.out.println("connect to server error");
        }
    }
    
    public static void main(String[] args) {
        
        PlayerFrame gf = new PlayerFrame(1500, 1200);
        gf.connectToServer();
        gf.setUpGUI();
        //gf.setUpButtons();
        
    }
    
}
