import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {

    public void playBG() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(
                    getClass().getResource("/sound/(BETA) Brawl Stars OST - Menu V1.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);

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

    public void playjeje() {
        try {
            AudioInputStream sound = AudioSystem
                    .getAudioInputStream(getClass().getResource("/sound/jejeje.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playToggle() {
        try {
            AudioInputStream sound = AudioSystem
                    .getAudioInputStream(getClass()
                            .getResource("/sound/ButtonPlate Click (Minecraft Sound) - Sound Effect for editing.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playStartGame() {
        try {
            AudioInputStream sound = AudioSystem
                    .getAudioInputStream(getClass()
                            .getResource("/sound/Start.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-12f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
