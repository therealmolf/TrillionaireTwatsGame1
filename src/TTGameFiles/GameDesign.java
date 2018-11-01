

//This is where we add the game Physics


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.io.*;
import java.net.*;

public class GameDesign extends JComponent {
        

    private Container contentPane;
    private int playerID;
    private PlayerSprite1 me;
    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private Timer animationTimer;
    private boolean up, down, left, right;
    private Bg bg;

/* Sample Variables
    
       private Walls b1;
    
    private Maze r1;
    
    public int score1;
    public int score2;  
    private double py1;
    
    private ArrayList<Maze> mazeList;
    private ArrayList<Walls> wallsList;
    
    private boolean isColliding;
    
*/
   
    private TalkToServerThread ttst;
    private ListenToServerThread ltst;
    
    
    
    public GameDesign(Container cp, Socket s, DataOutputStream dos, DataInputStream dis, int id, double py1){
        contentPane = cp;
        socket = s;
        dataOut = dos;
        dataIn = dis;
        playerID = id;
        up = false;
        down = false;
        left = false;
        right = false;
        
        /*
        isColliding = false;
        py1 = py1;
        */
        
        bg = new Bg(0, 0);
        
        if(playerID == 1)
        {
            /*
            me = new PlayerSprite1(33, 766);
            enemy = new PlayerSprite2(1346, 766);
            */
        }
        
        else if (playerID == 2)
        {
            /*
            me = new PlayerSprite1(1346, 766);
            enemy = new PlayerSprite2(33, 766);
            */
        }
        
        /*
        b1 = new Walls(0, 0, 19, 874);
           
        r1 = new Maze(19, 182, 94, 26);
        
        //new
        
        bar = new Maze(0, 882, 1500, 7);
        
        
        
        wallsList = new ArrayList<>();
        wallsList.add(b1);
       
        
        //arraylist of maze to be used in collision detection
        mazeList = new ArrayList<>();
        mazeList.add(r1);

        */
        
        ttst = new TalkToServerThread();
        ltst = new ListenToServerThread();
    }
    
    /*method used for collision detection throughout game
    public boolean checkCollision(SolidObject co1, SolidObject co2) {
        Rectangle r1 = co1.getBorder();
        Rectangle r2 = co2.getBorder();

       
        if (r1.intersects(r2)) {
            return isColliding = true;
        } else {
            return isColliding = false;
        }
        
    }
    */
    
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform reset = g2d.getTransform();
        
        bg.drawBackground(g2d, reset);
        
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        
        /*
        
        // four border walls
        g2d.setColor(new Color(255, 153, 81)); //everything declared under will be this color disregarding another setColor
        b1.drawObject(g2d);
        

        // bar
        g2d.setColor(Color.MAGENTA);
        bar.drawObject(g2d);
        
        //arena structure
        g2d.setColor(new Color(255, 216, 135));
        r1.drawObject(g2d);
        
        
        
        me.drawObject(g2d, reset, playerID);
        enemy.drawObject(g2d, reset, playerID);
        
        g2d.setColor(Color.BLACK);
        bl.drawObject(g2d);
        
        //new
        
        Font typef = new Font("Pixel", Font.PLAIN, 100);
        g2d.setFont(typef);
        
        

        
        for (int i = 0; i < wallsList.size(); i++)
            if (checkCollision(me, wallsList.get(i))) {
                up = false;
                down = false;
                left = false;
                right = false;
                
                if (me.getX() == 19){
                    me.setX(19);
                }else if (me.getX() == 1374){
                    me.setX(1374);
                }else if (me.getY() == 19){
                    me.setY(19);
                }else if (me.getY() == 761){
                    me.setY(761);
                }}
        
        for (int i = 0; i < mazeList.size(); i++)
            if (checkCollision(me, mazeList.get(i))) {
                up = false;
                down = false;
                left = false;
                right = false;
                }
        
        if (checkCollision(me, sword)) {
                g2d.setFont(typef);
                if (playerID == 1) {
                    g2d.setColor(Color.YELLOW);
                    g2d.drawString("YOU WIN", 550, 400);
                    bl.setX(0);
                    
                } else {
                    g2d.setColor(Color.RED);
                    g2d.drawString("YOU WIN", 550, 400);
                    bl.setX(0);
                }

            } else if (checkCollision(enemy, sword)) {
                g2d.setFont(typef);
                if (playerID == 1) {
                    g2d.setColor(Color.YELLOW);
                    g2d.drawString("YOU LOSE", 550, 400);
                    bl.setX(0);
                } else {
                    g2d.setColor(Color.RED);
                    g2d.drawString("YOU LOSE", 550, 400);
                    bl.setX(0);
                }
            }
        */
        
    }
    
 
    
    public void startThreads() {
        Thread listen = new Thread(ltst);
        Thread talk = new Thread(ttst);
        listen.start();
        talk.start();
        //YOU WOULD WANT TO START THE THREAD AFTER THE GUI/FRAME SHOWS UP; NOT IMMEDIATELY; YOU WOULD WANT TO TRANSMIT COORDINATES WHEN THE GAME BEGINS
    }

    public void setUpRepaintTimer() {
        int interval = 10;
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        double speed = 4;
                        if(up) {
                            //me.moveV(-speed);
                        } else if(down) {
                            //me.moveV(speed);
                        } else if(left) {
                            //me.moveH(-speed);
                        } else if(right) {
                            //me.moveH(speed);
                        }
                        GameDesign.this.repaint();
                    }
                });
            }
        };
        animationTimer = new Timer(interval, al);
        animationTimer.start();
    }

    public void setUpKL() {
        KeyListener kl = new KeyListener() {
            public void keyTyped(KeyEvent ke) {

            }

            public void keyPressed(KeyEvent ke) {
                int keyCode = ke.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_W:
                        up = true;
                        down = false;
                        left = false;
                        right = false;
                        break;
                    case KeyEvent.VK_S:
                        up = false;
                        down = true;
                        left = false;
                        right = false;
                        break;
                    case KeyEvent.VK_A:
                        up = false;
                        down = false;
                        left = true;
                        right = false;
                        break;
                    case KeyEvent.VK_D:
                        up = false;
                        down = false;
                        left = false;
                        right = true;
                        break;
                    default:
                        break;
                }
            }

            public void keyReleased(KeyEvent ke) {
                int keyCode = ke.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_W:
                        up = false;
                        break;
                    case KeyEvent.VK_S:
                        down = false;
                        break;
                    case KeyEvent.VK_A:
                        left = false;
                        break;
                    case KeyEvent.VK_D:
                        right = false;
                        break;
                    default:
                        break;
                }
            }
        };
        contentPane.addKeyListener(kl);
        contentPane.setFocusable(true);
        
    }
    
        
    /* ============================== TALKTOSERVERTHREAD INNER CLASS ============================== */  
    private class TalkToServerThread implements Runnable {

        public TalkToServerThread() {
            System.out.println("TTST created.");
        }

        public void run() {
                try {
                while(true)  //FOR SENDING MY COORDINATES
                {
                    /*
                    dataOut.writeDouble(me.getX());
                    dataOut.writeDouble(me.getY());
                    dataOut.flush();
                    */
                    
                    try {
                        Thread.sleep(25);
                    } catch(InterruptedException ex) {
                        System.out.println("ex - TTST INTERRUPTED");
                    }
                }
                
            } catch(IOException ex) {
                System.out.println("ex - TTST run");
            }
        }
    }
    
    
/* ============================== LISTENTOSERVERTHREAD INNER CLASS ============================== */ 
    private class ListenToServerThread implements Runnable {

        public ListenToServerThread() {
            System.out.println("LTST created.");
        }

        public void run() {

            try {
                while(true) {
                    /*
                    double x = dataIn.readDouble();
                    double y = dataIn.readDouble();
                    enemy.setX(x);
                    enemy.setY(y);
                    */
                }
            } catch(IOException ex) {
                //...
            }
        }
    }
}