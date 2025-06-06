import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Timer;

public class UI {
    // Non Number Verable
    Window window;
    // private Verable
    int LScore, RScore;
    boolean showInfo;
    Sound sound;

    // const Verable
    int scorePoisitionX = 100;
    int scorePoisitionY = 100;

    public UI(Window window, Sound sound) {
        this.window = window;
        this.sound = sound;
        LScore = 0;
        RScore = 0;
        showInfo = false;
    }

    public void paint(Graphics2D g) {
        if (window.gameState == window.gameState.START) {
            g.setColor(new Color(0, 0, 0, 170));
            g.fillRect(0, 0, window.getWidth(), window.getHeight());

            g.setColor(Color.PINK);
            g.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 32));
            g.drawString("按下 I 顯示操作說明", 670, 585);

            if (!showInfo) {

                g.setColor(Color.YELLOW);
                g.setFont(new Font("Microsoft JhengHei", Font.BOLD, 100));
                g.drawString("羽毛球低低手", 200, 200);

                g.setColor(Color.ORANGE);
                g.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 30));
                g.drawString("按下Enter開始遊戲", 360, 270);

            } else {
                g.setColor(Color.ORANGE);
                g.setFont(new Font("Microsoft JhengHei", Font.BOLD, 50));
                g.drawString("1P", 320, 220);
                g.drawString("2P ", 600, 220);
                g.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 40));
                g.drawString("移動 AD", 265, 280);
                g.drawString("跳躍 SPACE", 265, 320);
                g.drawString("小球 Shift", 265, 360);
                g.drawString("高球 ctrl", 265, 400);

                g.drawString("移動 < >", 545, 280);
                g.drawString("跳躍 up", 545, 320);
                g.drawString("小球 P", 545, 360);
                g.drawString("高球 O", 545, 400);
            }

        } else if (window.gameState == window.gameState.PLAYING) {
            // Show score
            g.setColor(Color.GREEN);
            g.setFont(new Font("Verdana", Font.BOLD, 50));
            g.drawString(Integer.toString(LScore), scorePoisitionX, scorePoisitionY);
            g.setColor(Color.YELLOW);
            g.drawString(Integer.toString(RScore), window.getWidth() - scorePoisitionX, scorePoisitionY);
        }
    }

    public void addScore(boolean LR) {
        if (!LR) {
            LScore++;
        } else {
            RScore++;
        }
    }

    public void toggleInfo() {
        showInfo = !showInfo;
        sound.playToggle();
    }
}
