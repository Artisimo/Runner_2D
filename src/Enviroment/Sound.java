package Enviroment;


import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

import static Game.Game.sound;


public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/sounds/movement/jump.wav"); //7,12,17,22,31,34,50
        soundURL[20] = getClass().getResource("/sounds/music/Whisperer.wav");

    }

    public void setFile(int i){
        try {
            System.out.println(soundURL[i]);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (UnsupportedAudioFileException e){

        }catch (IOException e){

        }catch (LineUnavailableException e){

        }

    }

    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

    public void playMusic(){
        setFile(20);
        play();
        loop();
    }
}
