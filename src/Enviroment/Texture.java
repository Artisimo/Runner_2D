package Enviroment;

import java.awt.image.BufferedImage;

public class Texture {
    SpriteSheet playerSheet;
    SpriteSheet platformSheet;

    SpriteSheet crystalSheet;
    private BufferedImage platform = null;
    private BufferedImage player = null;

    private BufferedImage crystal = null;

    public BufferedImage[] platformImages = new BufferedImage[1];
    public BufferedImage[] playerImages = new BufferedImage[17];

    public BufferedImage[] crystalImages = new BufferedImage[4];

    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            platform = loader.loadImage("platform.png");
            player = loader.loadImage("player1.png");
            crystal = loader.loadImage("crystal.png");
        }catch(Exception e){
            e.printStackTrace();
        }

        playerSheet = new SpriteSheet(player);
        platformSheet = new SpriteSheet(platform);
        crystalSheet = new SpriteSheet(crystal);

        getTextures();
    }

    public void getTextures(){
        platformImages[0] = platformSheet.grabImage(1, 1, 64, 64); // Platform texture

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

    }
}
