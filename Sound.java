import javax.sound.sampled.*;
import java.io.IOException;

public class Sound {
    Clip bgMusic;
    Clip Menu;
    Clip loseMusic;

    public void playBG() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(
                    getClass().getResource("/sound/BGM.wav"));
            bgMusic = AudioSystem.getClip();
            bgMusic.open(sound);

            FloatControl gainControl = (FloatControl) bgMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);

            bgMusic.start();
            bgMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopBG() {
        if (bgMusic != null && bgMusic.isRunning()) {
            bgMusic.stop();
            bgMusic.close();
        }
    }

    public void playMenu() {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(
                    getClass().getResource("/sound/Menu.wav"));
            Menu = AudioSystem.getClip();
            Menu.open(sound);

            FloatControl gainControl = (FloatControl) Menu.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);

            Menu.start();
            Menu.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMenu() {
        if (Menu != null && Menu.isRunning()) {
            Menu.stop();
            Menu.close();
        }
    }

    public void playLose() {
        try {
            AudioInputStream sound = AudioSystem
                    .getAudioInputStream(getClass()
                            .getResource("/sound/Lose.wav"));
            loseMusic = AudioSystem.getClip();
            loseMusic.open(sound);
            FloatControl gainControl = (FloatControl) loseMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-12f);
            loseMusic.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopLose() {
        if (loseMusic != null && loseMusic.isRunning()) {
            loseMusic.stop();
            loseMusic.close();
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
