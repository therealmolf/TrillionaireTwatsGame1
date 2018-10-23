package TTGameFiles;
import java.awt.Canvas;


public class Game extends Canvas implements Runnable{


    private static final long serialVersionUID = -4372403046120095479L;

    public static final int WIDTH = 640, HEIGHT = WIDTH * 2;

    public Game(){
        new Window(WIDTH, HEIGHT,"Game_Name", this);
    }

    public synchronized void start() {
    }
        public void run(){

        }



    public static void Main(String args[]){
        new Game();

    }
}