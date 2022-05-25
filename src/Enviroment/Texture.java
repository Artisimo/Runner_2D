package Enviroment;

import java.awt.image.BufferedImage;

public class Texture {
    SpriteSheet playerSheet;
    SpriteSheet platformSheet;
    private BufferedImage platform = null;
    private BufferedImage player = null;

    public BufferedImage[] platformImages = new BufferedImage[1];
    public BufferedImage[] playerImages = new BufferedImage[8];

    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            platform = loader.loadImage("platform.png");
            player = loader.loadImage("player.png");
        }catch(Exception e){
            e.printStackTrace();
        }

        playerSheet = new SpriteSheet(player);
        platformSheet = new SpriteSheet(platform);

        getTextures();
    }

    public void getTextures(){
        platformImages[0] = platformSheet.grabImage(1, 1, 64, 64); // Platform texture


        playerImages[0] = playerSheet.grabImage(1, 1, 64, 128);
        playerImages[1] = playerSheet.grabImage(1, 1, 64, 128);//right
        playerImages[2] = playerSheet.grabImage(3, 1, 64, 128);
        playerImages[3] = playerSheet.grabImage(4, 1, 64, 128);

    }
}
