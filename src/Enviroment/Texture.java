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
    public BufferedImage[] playerImages = new BufferedImage[10];

    public BufferedImage[] crystalImages = new BufferedImage[4];

    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            platform = loader.loadImage("platform.png");
            player = loader.loadImage("player.png");
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

        playerImages[0] = playerSheet.grabImage(1, 1, 64, 128);//still
        playerImages[1] = playerSheet.grabImage(2, 1, 64, 128);//right
        playerImages[2] = playerSheet.grabImage(3, 1, 64, 128);
        playerImages[3] = playerSheet.grabImage(4, 1, 64, 128);
        playerImages[4] = playerSheet.grabImage(5, 1, 64, 128);
        playerImages[5] = playerSheet.grabImage(6, 1, 64, 128);
        playerImages[6] = playerSheet.grabImage(7, 1, 64, 128);
        playerImages[7] = playerSheet.grabImage(8, 1, 64, 128);
        playerImages[8] = playerSheet.grabImage(9, 1, 64, 128);

    }
}
