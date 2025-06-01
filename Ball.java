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

public class Ball {
    // non Number Verable
    Window window;
    Timer ballFlyTimer;
    Racket racketRight;
    Racket racketLeft;

    // private Verable
    private int ballX, ballY;
    private int nowBallSpeed;

    // const Verable
    private int ballSpeed = 3;
    private int ballWidth = 20;
    private int ballHeigth = 20;

    public Ball(Window window, Racket racketRight, Racket racketLeft) {
        this.window = window;
        this.racketRight = racketRight;
        this.racketLeft = racketLeft;
        ballX = 400;
        ballY = 500;
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.fillOval(ballX, ballY, ballWidth, ballHeigth);

        g.setColor(Color.RED);
        g.draw(getBounds());
    }

    public void ballMove() {
        if (collisionTouchRacketLeft() && racketLeft.isSwing()) { // low
            if (!racketLeft.swingType()) {
                System.out.print("Left Low, ");
            } else {
                System.out.print("Left High, ");
            }
            ballFly();
        }

        if (collisionTouchRacketRight() && racketRight.isSwing()) {
            if (!racketRight.swingType()) {
                System.out.print("right Low, ");
            } else {
                System.out.print("right High, ");
            }
            ballFly();
        }
    }

    private void ballFly() {
        if (ballFlyTimer != null && ballFlyTimer.isRunning()) {
            return;
        }

        ballFlyTimer = new Timer(50, e -> {
            // ballX += 10;
        });

        ballFlyTimer.start();
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
