import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Color;

public class Net extends JPanel {
    private Window window;

    private int netX;
    private int netY;
    private final int width = 20;
    private final int height = 200;

    public Net(Window w) {
        this.window = w;
        netX = w.getWidth() / 2 - width;
        netY = w.getHeight() - height;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.pink);
        g.fillRect(netX, netY, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return netX;
    }
}
