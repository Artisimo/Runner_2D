package Enviroment;

import java.awt.image.BufferedImage;

public class Texture {
    SpriteSheet playerSheet;
    SpriteSheet platformSheet;

    SpriteSheet crystalSheet;
    SpriteSheet powerUpSheet;
    SpriteSheet explosiveEnemySheet;
    SpriteSheet basicEnemySheet;
    SpriteSheet shootingEnemySheet;
    private BufferedImage platform = null;
    private BufferedImage player = null;

    private BufferedImage crystal = null;

    private BufferedImage powerUp = null;

    private BufferedImage shootingEnemy = null;
    private BufferedImage basicEnemy = null;
    private BufferedImage explosiveEnemy = null;
    public BufferedImage projectile = null;

    public BufferedImage finishLine = null;

    public BufferedImage[] platformImages = new BufferedImage[1];
    public BufferedImage[] playerImages = new BufferedImage[17];

    public BufferedImage[] crystalImages = new BufferedImage[4];
    public BufferedImage[] powerUpImages = new BufferedImage[4];

    public BufferedImage[] shootingEnemyImages = new BufferedImage[2];
    public BufferedImage[] basicEnemyImages = new BufferedImage[8];
    public BufferedImage[] explosiveEnemyImages = new BufferedImage[6];

    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            platform = loader.loadImage("/textures/BasicGameTextures/platform.png");
            player = loader.loadImage("/textures//BasicGameTextures/player1.png");
            crystal = loader.loadImage("/textures/PowerUpCollectibles/crystal.png");
            powerUp = loader.loadImage("/textures/PowerUpCollectibles/powerUps.png");
            shootingEnemy = loader.loadImage("/textures/Enemy/shootingEnemy.png");
            basicEnemy = loader.loadImage("/textures/Enemy/basicEnemy.png");
            projectile = loader.loadImage("/textures/Enemy/projectile.png");
            explosiveEnemy = loader.loadImage("/textures/Enemy/explosiveEnemy.png");
            finishLine = loader.loadImage("/textures//BasicGameTextures/finishLine.png");
        }catch(Exception e){
            e.printStackTrace();
        }

        playerSheet = new SpriteSheet(player);
        platformSheet = new SpriteSheet(platform);
        crystalSheet = new SpriteSheet(crystal);
        powerUpSheet = new SpriteSheet(powerUp);
        shootingEnemySheet = new SpriteSheet(shootingEnemy);
        basicEnemySheet = new SpriteSheet(basicEnemy);
        explosiveEnemySheet = new SpriteSheet(explosiveEnemy);

        getTextures();
    }

    public void getTextures(){
        platformImages[0] = platformSheet.grabImage(2, 1, 64, 64); // Platform texture

        crystalImages[0] = crystalSheet.grabImage(1,1,64,64);
        crystalImages[1] = crystalSheet.grabImage(2,1,64,64);
        crystalImages[2] = crystalSheet.grabImage(3,1,64,64);
        crystalImages[3] = crystalSheet.grabImage(4,1,64,64);

        playerImages[0] = playerSheet.grabImage(1, 3, 64, 128); //Still animation

        playerImages[1] = playerSheet.grabImage(1, 1, 64, 128); //Right animation
        playerImages[2] = playerSheet.grabImage(2, 1, 64, 128); //Right animation
        playerImages[3] = playerSheet.grabImage(3, 1, 64, 128); //Right animation
        playerImages[4] = playerSheet.grabImage(4, 1, 64, 128); //Right animation
        playerImages[5] = playerSheet.grabImage(5, 1, 64, 128); //Right animation
        playerImages[6] = playerSheet.grabImage(6, 1, 64, 128); //Right animation

        playerImages[7] = playerSheet.grabImage(1, 2, 64, 128); //Left animation
        playerImages[8] = playerSheet.grabImage(2, 2, 64, 128); //Left animation
        playerImages[9] = playerSheet.grabImage(3, 2, 64, 128); //Left animation
        playerImages[10] = playerSheet.grabImage(4, 2, 64, 128); //Left animation
        playerImages[11] = playerSheet.grabImage(5, 2, 64, 128); //Left animation
        playerImages[12] = playerSheet.grabImage(6, 2, 64, 128); //Left animation
        playerImages[13] = playerSheet.grabImage(1, 4, 64, 128); // jumping right
        playerImages[14] = playerSheet.grabImage(2, 4, 64, 128); // jumping left
//        playerImages[7] = playerSheet.grabImage(8, 1, 64, 128);
//        playerImages[8] = playerSheet.grabImage(9, 1, 64, 128);

        powerUpImages[0] = powerUpSheet.grabImage(1,1,64,64);  // Heal
        powerUpImages[1] = powerUpSheet.grabImage(2,1,64,64);  // max health increase
        powerUpImages[2] = powerUpSheet.grabImage(3,1,64,64);  // speed
        powerUpImages[3] = powerUpSheet.grabImage(4,1,64,64);  // high jump

        shootingEnemyImages[0] = shootingEnemySheet.grabImage(1,1,64,64);  // shooting enemy that shoots right
        shootingEnemyImages[1] = shootingEnemySheet.grabImage(2,1,64,64);  // shooting enemy that shoots left

        basicEnemyImages[0] = basicEnemySheet.grabImage(1,1,64,64);  // basic enemy left run animation
        basicEnemyImages[1] = basicEnemySheet.grabImage(1,2,64,64);  // basic enemy left run animation
        basicEnemyImages[2] = basicEnemySheet.grabImage(1,3,64,64);  // basic enemy left run animation
        basicEnemyImages[3] = basicEnemySheet.grabImage(1,4,64,64); // basic enemy left run animation

        basicEnemyImages[4] = basicEnemySheet.grabImage(2,1,64,64);  // basic enemy right run animation
        basicEnemyImages[5] = basicEnemySheet.grabImage(2,2,64,64);  // basic enemy right run animation
        basicEnemyImages[6] = basicEnemySheet.grabImage(2,3,64,64);  // basic enemy right run animation
        basicEnemyImages[7] = basicEnemySheet.grabImage(2,4,64,64);  // basic enemy right run animation

        explosiveEnemyImages[0] = explosiveEnemySheet.grabImage(1,1,64,64);  // explosive enemy right run animation
        explosiveEnemyImages[1] = explosiveEnemySheet.grabImage(2,1,64,64);  // explosive enemy left run animation
        explosiveEnemyImages[2] = explosiveEnemySheet.grabImage(3,1,64,64);  // explosion animation

    }
}
