/* 
    The Beginning of the end
*/

import ttc.game.*;
import java.io.*;
import java.net.*;
import java.util.Random;

public class GameServer {

    private ServerSocket ss;
    private Socket p1Socket, p2Socket;
    private final int maxPlayers = 2;
    private int numPlayers;

    // The server will use 2 threads for each player.
    // This will lead to a total of 4 threads if there are 2 players.
    // Why 2 threads per player?
    //      - one thread for the output stream
    //      - one thread for the input stream
    // They need to be separate because we will be 
    // CONSTANTLY sending out each player's coordinates.
    private TalkToClientThread p1Talk;
    private TalkToClientThread p2Talk;
    private ListenToClientThread p1Listen;
    private ListenToClientThread p2Listen;
    
    // These fields will store the coordinates of each player.
    private double p1x, p1y, p2x, p2y,py1;
    
    private int p1Score, p2Score, aurax, auray;
    private int panalo;
    private boolean finished;  
    private boolean collectedSword;

    public GameServer() {
        System.out.println("===== GAME SERVER =====");
        numPlayers = 0;
        /* outlier values because players have not sent their coordinate
        p1x = -9999;
        p1y = -9999;
        p2x = -9999;
        p2y = -9999;
        
        Random rand = new Random();
       
        int  r2 = rand.nextInt(1400) + 30;
            
        py1 = r2;
        
        */
        
        try {
            ss = new ServerSocket(8888);
        } catch (IOException ex) {
            System.out.println("ex - GameServer constructor");
        }
    }

    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");
   
            while (numPlayers < maxPlayers) {
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                
                numPlayers++;
                out.writeInt(numPlayers);
                out.writeDouble(py1);
                System.out.println("Player #" + numPlayers + " has connected.");
                TalkToClientThread ttct = new TalkToClientThread(numPlayers, out);
                ListenToClientThread ltct = new ListenToClientThread(numPlayers, in);
                
                if(numPlayers == 1)
                {
                    p1Socket = s;
                    p1Talk = ttct;
                    p1Listen = ltct;
                }
                
                else
                {
                    p2Socket = s;
                    p2Talk = ttct;
                    p2Listen = ltct;
                    //BECAUSE AFTER GETTING PLAYER 2, YOU WANT TO START LISTENING; PLAYER 2 MUST JOIN FIRST FOR THE GAME TO START
                    //THREADS FOR JUST THE SERVER; MUST CREATE AND START THE THREADS ON THE CLIENT SIDE AS WELL
                   // p1Talk.sendStartMsg();
                    //p2Talk.sendStartMsg();
                    Thread lt1 = new Thread(p1Listen);
                    Thread lt2 = new Thread(p2Listen);
                    lt1.start();
                    lt2.start();
                    
                    
                    Thread tt1 = new Thread(p1Talk);
                    Thread tt2 = new Thread(p2Talk);
                    tt1.start();
                    tt2.start();
                    
                }
            }
            
        } catch (IOException ex) {
            System.out.println("ex - acceptConnections()");
        }
    }

  

/* ============================== TALKTOCLIENTTHREAD INNER CLASS ============================== */    
    private class TalkToClientThread implements Runnable {

        private int playerID;
        private DataOutputStream dataOut;

        public TalkToClientThread(int pid, DataOutputStream out) {
            playerID = pid;
            dataOut = out;
        }

        public void run() {          

            while(true){ //WHILE LOOP TO BE ABLE TO KEEP SENDING X AND Y COORDINATES
                /*
                
                if(p1x != -9999 && p1y != -9999 && p2x != -9999 && p2y != -9999) //CHECKING IF THE INITIAL VALUES ARE DIFFERENT > MEANING YOU'VE ALREADY RECEIVED THE COORDINATES
                {
                    if(playerID == 1)
                    {
                        p1Talk.sendEnemyCoordinates(p2x, p2y);
                    }
                    else
                    {
                        p2Talk.sendEnemyCoordinates(p1x, p1y);
                    }
                }
                
                //MAKE THE THREADS SLEEP JUST BEFORE THE LOOP ENDS
                */
                try {
                    Thread.sleep(25);
                } catch(InterruptedException ex){
                    //...ex message
                }
            }
        }
        
        /*
        public void sendEnemyCoordinates(double x, double y)
        {
            try {
                dataOut.writeDouble(x);
                dataOut.writeDouble(y);
                
            } catch(IOException ex){
                System.out.println("ex = TTCT sendEnemyCoordinates");
            }
        }
        
        
        public void sendStartMsg()
        {
            try {
                dataOut.writeUTF("TWO PLAYERS HAVE CONNECTED. YOU MAY NOW BEGIN THE GAME");
            } catch(IOException ex){
                System.out.println("ex - TalkToClientThread sendStartMsg");
            }
        }
*/
    }

    
/* ============================== LISTENTOCLIENTTHREAD INNER CLASS ============================== */    
    private class ListenToClientThread implements Runnable {

        private int playerID;
        private DataInputStream dataIn;

        public ListenToClientThread(int pid, DataInputStream in) {
            playerID = pid;
            dataIn = in;

        }

        public void run() {
        
            try {
                while(true) { 
                    if(playerID == 1)
                    {
                        /*
                        p1x = dataIn.readDouble();
                        p1y = dataIn.readDouble();
                        */
                        
                    } else {
                        /*
                        p2x = dataIn.readDouble();
                        p2y = dataIn.readDouble();
                        */
                    }
                }
            } catch(IOException ex){
                System.out.println("ex - ListenToClientThread run");
            }
        }
        
    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }

}
