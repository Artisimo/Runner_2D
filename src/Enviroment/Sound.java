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
        soundURL[20] = getClass().getResource("/sounds/music/Whisperer.wav");//menu music
        soundURL[21] = getClass().getResource("/sounds/music/Warmth.wav");
        soundURL[22] = getClass().getResource("/sounds/music/Nostalgic.wav");//menu music ends
        soundURL[23] = getClass().getResource("/sounds/music/Electric.wav");//Soundtrack
        soundURL[24] = getClass().getResource("/sounds/music/Spaceship.wav");
        soundURL[25] = getClass().getResource("/sounds/music/Game Loop.wav");
        soundURL[26] = getClass().getResource("/sounds/music/Sweet Treats.wav");//Soundtrack ends
    }

    public void setFile(int i){
        try {
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
        clip = null;
    }

    public void playMusic(int i){
        setFile(i);
        play();
        loop();
    }

    public Clip getClip() {
        return clip;
    }
}
