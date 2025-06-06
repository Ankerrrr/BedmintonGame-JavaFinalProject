import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.Timer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.util.Random;

public class Ball extends JPanel {
    // non Number Verable
    Window window;
    Timer ballFlyTimer;
    Racket racketRight;
    Racket racketLeft;
    Net net;
    Timer racketTouchColdDownTimer;
    UI ui;
    Sound sound;
    VisualResource resource;

    // private Verable
    private int ballX, ballY;
    private int targetX;
    private double a;
    private double vertexX, vertexY; // 拋物線頂點
    private boolean LR;

    // const Verable
    private int ballWidth = 25;
    private int ballHeigth = 25;
    private int startPoisitionX = 300;
    private int startPoisitionY = 400;

    public Ball(Window window, Racket racketRight, Racket racketLeft, Net net, UI ui, Sound sound,
            VisualResource resource) {
        this.window = window;
        this.racketRight = racketRight;
        this.racketLeft = racketLeft;
        this.net = net;
        this.ui = ui;
        this.sound = sound;
        this.resource = resource;
        ballX = startPoisitionX;
        ballY = startPoisitionY;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.YELLOW);
        // g.fillOval(ballX, ballY, ballWidth, ballHeigth);
        g.drawImage(resource.ballImage, ballX, ballY, ballWidth, ballHeigth, this);

        // g.setColor(Color.RED);
        // g.draw(getBounds());
    }

    public boolean racketTouchColdDown() {
        if (racketTouchColdDownTimer != null && racketTouchColdDownTimer.isRunning()) {
            return false;
        }
        racketTouchColdDownTimer = new Timer(600, e -> {
            racketTouchColdDownTimer.stop();
        });

        racketTouchColdDownTimer.start();
        return true;
    }

    public void ballMove() {
        if (collisionTouchRacketLeft() && racketLeft.isSwing()) { // low
            if (!racketLeft.swingType()) {
                setBallState(false, false);
            } else {
                setBallState(false, true);
            }
            if (racketTouchColdDown() && ballFlyTimer != null && ballFlyTimer.isRunning()) {
                ballFlyTimer.stop();
            }
            ballFly();
        }

        if (collisionTouchRacketRight() && racketRight.isSwing()) {
            if (!racketRight.swingType()) {
                setBallState(true, false);
            } else {
                setBallState(true, true);
            }
            if (racketTouchColdDown() && ballFlyTimer != null && ballFlyTimer.isRunning()) {
                ballFlyTimer.stop();
            }
            ballFly();
        }
    }

    private void setBallState(boolean LR, boolean lowOrHigh) {
        sound.playSwing();
        this.LR = LR;
        Random rand = new Random();
        int xOffectRandom = 5 + (int) rand.nextInt(250);

        if (!LR) {
            targetX = net.getX() + Math.abs(ballX) + xOffectRandom;
            System.out.println(targetX);
        } else {
            targetX = net.getX() - Math.abs(window.getWidth() - ballX) - xOffectRandom;
        }

        if (lowOrHigh) {
            vertexY = 100;
        } else {
            vertexY = 300;
        }

        vertexX = Math.abs(ballX + targetX) / 2.0;

        double dx = Math.abs(ballX - vertexX);
        ballY = 600;
        a = Math.abs(ballY - vertexY) / (dx * dx);
    }

    private void ballFly() {
        if (ballFlyTimer != null && ballFlyTimer.isRunning()) {
            return;
        }
        // 拋物線公式 y = a(x - h)^2 + k
        int limitCount = 40;
        while (!LR && (racketLeft.getY() + 50) < ballY || LR && (racketRight.getY() + 50) < ballY) {
            // 拋物線底開始計算 要讓球變成從拍子飛出去
            if (ballX < targetX) {
                ballX += 1;
            } else if (ballX > targetX) {
                ballX -= 1;
            }
            ballY = (int) (a * Math.pow(ballX - vertexX, 2) + vertexY);
            // System.out.print("Loop: " + racketLeft.getY() + "ball:" + ballY);
            if (limitCount-- <= 0) {
                break;
            }
        }

        ballFlyTimer = new Timer(16, e -> {
            // 水平移動
            if (ballX < targetX) {
                ballX += 10;
            } else if (ballX > targetX) {
                ballX -= 10;
            }

            // 拋物線公式 y = a(x - h)^2 + k
            ballY = (int) (a * Math.pow(ballX - vertexX, 2) + vertexY);

            if (Math.abs(ballY) >= 600) {
                Lose();
                ((Timer) e.getSource()).stop();
            }

            // System.out.print("X," + ballX + "Y" + ballY);

        });

        ballFlyTimer.start();
    }

    private void Lose() {
        ui.addScore(LR);
        sound.playBeep();
        if (!LR) {
            ballX = startPoisitionX;
        } else {
            ballX = window.getWidth() - startPoisitionX;
        }
        ballY = startPoisitionY;
    }

    private boolean collisionTouchRacketLeft() {
        Shape leftRacketBounds = window.playerL.getRacket().getRotatedBounds();

        return leftRacketBounds.intersects(getBounds());
    }

    private boolean collisionTouchRacketRight() {
        Shape rightRacketBounds = window.playerR.getRacket().getRotatedBounds();

        return rightRacketBounds.intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(ballX, ballY, ballWidth, ballHeigth);
    }

    public void setBallPoisition(int x, int y) {
        ballX = x;
        ballY = y;
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
    }
}
