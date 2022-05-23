import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;

    private Random r = new Random();

    public Game(){
        new Window(WIDTH, HEIGHT, "First game", this);
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();   //Start time in nanoseconds
        double amountOfTicks = 60.0;        // Amount of ticks per second
        double ns = 1000000000 / amountOfTicks;  // Amount of nanoseconds in one tick
        double delta = 0;
        long timer = System.currentTimeMillis();   //Used to display fps which refreshes once every second
        int frames = 0;                             //Used to display fps which refreshes once every second
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    private void tick(){
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0, WIDTH, HEIGHT);

        g.dispose();
        bs.show();
    }

    public static int clamp(int val, int min, int max){
        if(val >= max){
            return max;
        }else if(val <= min){
            return min;
        }else{
            return val;
        }
    }

    public static void main(String args[]){
        new Game();
    }
}
