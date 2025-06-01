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
    Net net;

    // private Verable
    private int ballX, ballY;
    private int nowBallSpeed;
    private int targetX;
    private int targetY;
    private boolean ballGoingDown;
    private double a;
    private double vertexX, vertexY; // 拋物線頂點

    // const Verable
    private int ballSpeed = 3;
    private int ballWidth = 20;
    private int ballHeigth = 20;
    private int lowSwingHeight = 350;
    private int hightSwingHeight = 40;
    // private int ballXAdd = 200;

    public Ball(Window window, Racket racketRight, Racket racketLeft, Net net) {
        this.window = window;
        this.racketRight = racketRight;
        this.racketLeft = racketLeft;
        this.net = net;
        ballX = 300;
        ballY = 400;
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
                setBallState(false, false);
            } else {
                setBallState(false, true);
            }
            ballFly();
        }

        if (collisionTouchRacketRight() && racketRight.isSwing()) {
            if (!racketRight.swingType()) {
                setBallState(true, false);
            } else {
                setBallState(true, true);
            }
            ballFly();
        }
    }

    private void setBallState(boolean LR, boolean lowOrHigh) {
        int xOffect = 0;
        if (!LR) {
            targetX = net.getX() + ballX + xOffect;
        } else {
            targetX = net.getX() - (ballX - net.getX()) - xOffect;
        }
        System.out.println(targetX);
        if (lowOrHigh) {
            vertexY = 100;
        } else {
            vertexY = 300;
        }

        vertexX = Math.abs(ballX + targetX) / 2.0;
        targetY = window.getHeight(); // 著陸點與起點同高

        double dx = Math.abs(ballX - vertexX);
        a = (ballY - vertexY) / (dx * dx);
    }

    private void ballFly() {
        if (ballFlyTimer != null && ballFlyTimer.isRunning()) {
            return;
        }

        ballFlyTimer = new Timer(16, e -> {
            // 水平移動
            if (ballX < targetX) {
                ballX += 3;
            } else if (ballX > targetX) {
                ballX -= 3;
            }

            // 拋物線公式 y = a(x - h)^2 + k
            ballY = (int) (a * Math.pow(ballX - vertexX, 2) + vertexY);

            // 停止條件：到達 targetX 附近
            if (Math.abs(ballX - targetX) == 0) {
                ((Timer) e.getSource()).stop();
            }

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
