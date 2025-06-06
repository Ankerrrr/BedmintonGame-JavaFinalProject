import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {

    public void playBG() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(
                    getClass().getResource("/sound/8-Bit Video Game Music - 8 Bit Adventure - Royalty Free.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);

            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playMove() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(getClass().getResource("/sound/move.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);

            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playBeep() {
        try {
            AudioInputStream sound = AudioSystem
                    .getAudioInputStream(getClass().getResource("/sound/REFEREE WHISTLE SOUND EFFECT.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playSwing() {
        try {
            AudioInputStream sound = AudioSystem
                    .getAudioInputStream(getClass().getResource("/sound/Swing.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-5.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
