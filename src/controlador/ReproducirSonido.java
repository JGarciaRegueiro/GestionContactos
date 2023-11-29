package controlador;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ReproducirSonido {
	
	private Clip clip;
	private Clip clip2;

    public ReproducirSonido() {
     
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("sounds\\wow.wav"));
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream("sounds\\click.wav"));
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            AudioInputStream audioInputStream2 = AudioSystem.getAudioInputStream(bufferedInputStream2);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip2 = AudioSystem.getClip();
            clip2.open(audioInputStream2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reproducirSonido() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
    
    public void reproducirClick() {
        if (clip2 != null) {
            clip2.setFramePosition(0);
            clip2.start();
        }
    }

}
