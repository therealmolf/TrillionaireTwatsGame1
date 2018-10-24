package TTGameFiles;
import java.awt.Canvas;


public class Game extends Canvas implements Runnable{


    //private static final long serialVersionUID = -4372403046120095479L;

    public static final int WIDTH = 700, HEIGHT = 500;

    public Game(){
        new Window(WIDTH, HEIGHT,"Game_Name", this);
    }

    public synchronized void start() {
    }
        public void run(){

        }

        public static void main(String[] args){
        new Game();

    }
}