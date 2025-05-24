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

    // const Verable
    int scorePoisitionX = 100;
    int scorePoisitionY = 100;

    public UI(Window window) {
        this.window = window;
        LScore = 0;
        RScore = 0;
    }

    public void paint(Graphics2D g) {
        // Show score
        g.setColor(Color.GREEN);
        g.setFont(new Font("Verdana", Font.BOLD, 50));
        g.drawString(Integer.toString(LScore), scorePoisitionX, scorePoisitionY);
        g.setColor(Color.YELLOW);
        g.drawString(Integer.toString(RScore), window.getWidth() - scorePoisitionX, scorePoisitionY);
    }

    public void addScore(boolean LR) {
        if (!LR) {
            LScore++;
        } else {
            RScore++;
        }
    }
}
