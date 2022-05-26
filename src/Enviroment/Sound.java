package Enviroment;


import javax.sound.sampled.*;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;


public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("jump.wav");
    }

    public void setFile(int i){
        try {
            System.out.println();
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
}
