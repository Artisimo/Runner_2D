import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static int WIDTH, HEIGHT;
    private Thread thread;
    private boolean running = false;

    private Random r = new Random();

    Camera camera = new Camera(0,0);

    private Handler handler;

    public Game(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = screenSize.width;
        HEIGHT = screenSize.height;

        handler = new Handler();
        new Window(WIDTH, HEIGHT, "First game", this);
        this.addKeyListener(new KeyInput(handler));
        Player player = new Player(180, 200, 100, 100, ID.Player, handler);
        Hpbar hpbar = new Hpbar(0,0,0,32,ID.Hpbar,player);

        handler.addHpbar(hpbar);
        handler.addObject(player);
        handler.addObject(new Platform(0,300, 100, 100, ID.Platform, handler));
        handler.addObject(new Platform(0,400, 100, 100, ID.Platform, handler));
        handler.addObject(new Platform(100,400, 100, 100, ID.Platform, handler));
        handler.addObject(new Platform(200,100, 100, 100, ID.Platform, handler));
        handler.addObject(new Platform(200,400, 100, 100, ID.Platform, handler));
        handler.addObject(new Platform(300,400, 100, 100, ID.Platform, handler));
        handler.addObject(new Platform(400,400, 100, 100, ID.Platform, handler));
        handler.addObject(new Platform(400,300, 100, 100, ID.Platform, handler));
        handler.addObject(new BasicEnemy(200,200,50,50,ID.BasicEnemy,handler,175,20));
        handler.addObject(new ShootingEnemy(400,300,50,100,ID.BasicEnemy,handler,0,25,30,-1));
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

        handler.tick();

        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player){
                camera.tick(handler.object.get(i));
            }
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH, HEIGHT );

        g2d.translate(camera.getX(), camera.getY());

        handler.render(g);
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
