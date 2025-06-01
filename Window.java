import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class Window extends JFrame implements KeyListener {

    private BufferedImage buffer;
    private Graphics bufferGraphics;

    Net net;
    Player playerL;
    Player playerR;
    UI ui;
    Ball ball;

    public Window() {
        this.setTitle("羽毛球低低手!");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addKeyListener(this);
    }

    public void initObject() {
        net = new Net(this);
        playerL = new Player(this, net, false);
        playerR = new Player(this, net, true);
        ui = new UI(this);
        ball = new Ball(this, playerR.getRacket(), playerL.getRacket());

        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        // firstTime
        if (buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            bufferGraphics = buffer.getGraphics();
        }

        bufferGraphics.setColor(Color.BLACK);
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) bufferGraphics;

        net.paint(g2d);
        playerL.paint(g2d);
        playerR.paint(g2d);
        ui.paint(g2d);
        ball.paint(g2d);

        g.drawImage(buffer, 0, 0, this);
    }

    public static void main(String[] args) {
        Window frame = new Window();
        javax.swing.SwingUtilities.invokeLater(() -> {
            frame.initObject(); // 在 UI 初始化後建立 Player
        });
        new Timer(16, e -> {
            frame.repaint();
            frame.playerL.playerMove();
            frame.playerR.playerMove();
            frame.ball.ballMove();
        }).start(); // update 60Hz
    }

    @Override
    public void keyPressed(KeyEvent k) {
        playerL.keyPress(k);
        playerR.keyPress(k);
    }

    @Override
    public void keyReleased(KeyEvent k) {
        playerL.keyReleased(k);
        playerR.keyReleased(k);
    }

    @Override
    public void keyTyped(KeyEvent k) {

    }
}