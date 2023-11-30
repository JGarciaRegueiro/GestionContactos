package controlador;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
            // Obtener las rutas de los archivos de sonido desde el JAR
            InputStream wowInputStream = getClass().getResourceAsStream("/wow.wav");
            InputStream clickInputStream = getClass().getResourceAsStream("/click.wav");

            // Verificar que se hayan cargado los archivos de sonido correctamente
            if (wowInputStream != null && clickInputStream != null) {
                BufferedInputStream bufferedWowStream = new BufferedInputStream(wowInputStream);
                BufferedInputStream bufferedClickStream = new BufferedInputStream(clickInputStream);

                AudioInputStream audioWowInputStream = AudioSystem.getAudioInputStream(bufferedWowStream);
                AudioInputStream audioClickInputStream = AudioSystem.getAudioInputStream(bufferedClickStream);

                clip = AudioSystem.getClip();
                clip.open(audioWowInputStream);

                clip2 = AudioSystem.getClip();
                clip2.open(audioClickInputStream);
            } else {
                System.err.println("No se pudieron cargar los archivos de sonido.");
                // Manejar el error según tus necesidades
            }

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
