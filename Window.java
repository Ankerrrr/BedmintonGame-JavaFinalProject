import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements KeyListener {

    private BufferedImage buffer;
    private Graphics bufferGraphics;

    Net net;
    Player playerL;
    Player playerR;
    UI ui;
    Ball ball;
    VisualResource visualresoNotPlayer;
    static Sound sound;

    public enum GameState {
        START, PLAYING, SHOWSCORE
    }

    public GameState gameState = GameState.START;

    public Window() {
        this.setTitle("羽毛球低低手!");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addKeyListener(this);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/ball.png"));
        setIconImage(icon);

    }

    public void initObject() {
        sound = new Sound();
        ui = new UI(this, sound);
        net = new Net(this);
        playerL = new Player(this, net, false, sound, ui);
        playerR = new Player(this, net, true, sound, ui);
        visualresoNotPlayer = new VisualResource(false);
        ball = new Ball(this, playerR.getRacket(), playerL.getRacket(), net, ui, sound, visualresoNotPlayer);

        init();
    }

    public void init() {
        this.setVisible(true);
        sound.playMenu();
    }

    @Override
    public void paint(Graphics g) {
        // firstTime
        if (buffer == null || buffer.getWidth() != getWidth() || buffer.getHeight() != getHeight()) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            bufferGraphics = buffer.getGraphics();
        }

        bufferGraphics.drawImage(visualresoNotPlayer.backGroundImage, 0, 0, getWidth(), getHeight(), this);

        Graphics2D g2d = (Graphics2D) bufferGraphics;

        g2d.setColor(new Color(0, 0, 0, 170));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        net.paint(g2d);
        playerL.paint(g2d);
        playerR.paint(g2d);
        ball.paint(g2d);
        ui.paint(g2d);

        g.drawImage(buffer, 0, 0, this);
    }

    public static void main(String[] args) {
        System.out.println("Welcome, this game is making for java Final project, Thank for your playing");
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