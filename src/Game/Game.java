package Game;

import Enviroment.Window;
import Enviroment.*;
import GameObjects.CoreGameObjects.*;
import GameObjects.Enemies.BasicEnemy;
import GameObjects.Enemies.ExplosiveEnemy;
import GameObjects.Enemies.RuningExplosiveEnemy;
import GameObjects.Enemies.ShootingEnemy;
import GameObjects.PowerUps.*;
import Handler.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static int WIDTH, HEIGHT;
    public static int levelWidth, levelHeight;
    private Thread thread;
    private boolean running = false;

    private Random r = new Random();
    private Graphics g;
    public GameState gameState;

    public BufferedImage level;
    public BufferedImage background;

    static Texture tex;
    public static Sound music = new Sound();
    public static Sound sound = new Sound();
    public static Sound movingRight = new Sound();
    public static Sound movingLeft = new Sound();

    public static int levelsAmount = 10;
    public String levelname;
    Camera camera = new Camera(0,0);

    private Handler handler;
    public MenuHandler menuHandler;

    public String userName;

    public Game(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        WIDTH = screenSize.width;
        HEIGHT = screenSize.height;

        userName = "";

        handler = new Handler();
        menuHandler = new MenuHandler(this);
        new Window(WIDTH, HEIGHT, "First game", this);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(new MouseInput(this, g));
        tex = new Texture();

        gameState = GameState.USERNAME_PROMPT;

        music.playMenuMusic();
    }

    public void play(String path){
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage(path);
        handler.object.clear();
        loadLevelImage(level);
        levelWidth = loader.getLevelWidth(path);
        levelHeight = loader.getLevelHeight(path);
        background = loader.loadImage("/Textures/BasicGameTextures/background.png");
        gameState = GameState.PLAYING;
        music.stop();
        music.playGameMusic();
        levelname = path.substring(8,14);
        menuHandler.object.clear();

    }

    public void emptyHandler(){
        handler.object.clear();
        menuHandler.object.clear();
    }

    private void loadLevelImage(BufferedImage image){
        int w = image.getWidth();
        int h = image.getWidth();

        Player player;

        for(int x = 0; x < h; x++){
            for(int y = 0; y < w; y++){
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue =(pixel) & 0xff;


                if(red == 255 && green == 255 && blue == 255){
                    handler.addObject(new Platform(x*64,y*64, 64, 64, ID.Platform, handler));
                }

                if(red == 0 && green == 0 && blue == 255){
                    player = new Player(x*64, y*64, 64, 128, ID.Player, handler, this);
                    handler.addObject(player);
                    Hpbar hpbar = new Hpbar(0,0,0,32, ID.Hpbar,player);

                    handler.addObject(hpbar);
                }

                if(red == 255 && green ==0 && blue == 0){
                    handler.addObject(new BasicEnemy(x*64, y*64, 64, 128, ID.BasicEnemy, handler, 100, 10));
                }

                if(red == 0 && green == 255 && blue == 0){
                    handler.addObject(new ShootingEnemy(x*64, y*64, 64, 128, ID.BasicEnemy, handler, 170, 10, 20, 1));
                }

                if(red == 0 && green == 128 && blue == 0){
                    handler.addObject(new ShootingEnemy(x*64, y*64, 64, 128, ID.BasicEnemy, handler, 170, 10, 20, -1));
                }

                if(red == 255 && green == 255 && blue == 128){
                    handler.addObject(new ExplosiveEnemy(x*64, y*64, 64, 64, ID.ExplosiveEnemy, handler, 50, 50, 150));
                }

                if(red == 128 && green == 255 && blue == 255){
                    handler.addObject(new RuningExplosiveEnemy(x*64, y*64, 64, 64, ID.ExplosiveEnemy, handler, 20, 50, 150 ,5));
                }

                if(red == 255 && green == 128 && blue == 255){
                    handler.addObject(new FinishLine(x*64, y*64, 64, 128, ID.FinishLine, handler));
                }

                if(red == 255 && green == 255 && blue == 64){
                    handler.addObject(new Crystal(x*64, y*64, 64, 64, ID.Crystal, handler));
                }

                if(red == 255 && green == 216 && blue == 0){
                    handler.addObject(new HealPowerUp(x*64, y * 64, 64, 64, ID.HealPowerUp, handler));
                }
                if(red == 128 && green == 128 && blue == 128){
                    handler.addObject((new MaxHpPowerUp(x*64, y * 64,64,64,ID.MaxHpPowerUp)));
                }

                if(red == 0 && green == 155 && blue == 255){
                    handler.addObject(new SpeedPowerUp(x*64, y * 64,64,64,ID.SpeedPowerUp));
                }

                if(red == 88 && green == 140 && blue == 58){
                    handler.addObject(new JumpPowerUp(x*64, y * 64,64,64,ID.JumpPowerUp));
                }
            }
        }
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

        if(gameState == GameState.PLAYING){
            handler.tick();

            for(int i = 0; i < handler.object.size(); i++){
                if(handler.object.get(i).getId() == ID.Player){
                    camera.tick(handler.object.get(i), levelWidth, levelHeight);
                }
            }
        }else if(gameState == GameState.MENU){
            if(music.getClip() == null){
                music.playMenuMusic();
            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        if(gameState == GameState.USERNAME_PROMPT){
            menuHandler.generateUserNamePrompt(g);

        }else if(gameState == GameState.MENU){
            menuHandler.generateStartMenu(g);

        }else if(gameState == GameState.PLAYING){
            int imageX = (this.getWidth() - background.getWidth(null)) / 2;
            int imageY = (this.getHeight() - background.getHeight(null)) / 2;
            g.drawImage(background, imageX ,imageY, null);
            g2d.translate(camera.getX(), camera.getY());

            handler.render(g);
            g.dispose();
            bs.show();
        }else if(gameState == GameState.PAUSED){
            menuHandler.generatePauseMenu(g);
        }

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

    public static Texture getInstance(){
        return tex;
    }

    public Graphics getGraphics(){
        return g;
    }

    public static void main(String args[]){
        //mySqlDatabase.getDB();
        new Game();
    }
}
