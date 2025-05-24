import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;

public class Racket {
    // Non Number Verable
    Timer swingTimer;

    // const Verable
    private static final int racketWidth = 10;
    private static final int racketHeight = 100;

    // private Verable
    private int racketX, racketY;
    private boolean LR;
    private int angle;
    boolean goBack;
    boolean swingComplete;

    public Racket(boolean LR) {
        this.LR = LR;
        angle = 150;
    }

    public void updatePoistion(int playerX, int playerY, int playerWidth, int playerHeight) {
        if (angle > 360) {
            angle = 0;
        }
        if (angle < 0) {
            angle = 360;
        }
        if (!LR) {
            this.setRacketPoisition((playerWidth / 2) + playerX, playerY - 50);
        } else {
            this.setRacketPoisition((playerWidth / 2) - 10 + playerX, playerY - 50); // 10 is look Beauty
        }
    }

    public boolean swing(boolean swingType) { // 0 ->down, 1->up
        if (swingTimer != null && swingTimer.isRunning()) {
            return false;
        }
        goBack = false;
        swingComplete = false;
        swingTimer = new Timer(30, e -> {
            System.out.print(angle + ", ");
            if (!goBack) {

                angle -= 20;
                if ((angle >= 320 && swingType) || (angle <= 100 && !swingType)) {
                    goBack = true;
                }
            } else {

                angle += 30;

                if (angle == 150) {
                    swingComplete = true;
                    swingTimer.stop();
                }
            }

        });
        swingTimer.start();
        if (swingComplete) {
            return true;
        } else {
            return false;
        }
    }

    public Shape getRotatedBounds() {
        Rectangle2D rect = new Rectangle2D.Double(racketX, racketY, racketWidth, racketHeight);

        // 以球拍底部中間作為旋轉中心
        double rotateCenterX = racketX + (racketWidth / 2.0);
        double rotateCenterY = racketY + (racketHeight / 2) + 80;

        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(getAngle()), rotateCenterX, rotateCenterY);

        return transform.createTransformedShape(rect);
    }

    public Rectangle getBounds() {
        return new Rectangle(racketX, racketY, racketWidth, racketHeight);
    }

    public void setRacketPoisition(int x) {
        this.racketX = x;
    }

    public void setRacketPoisition(int x, int y) {
        this.racketX = x;
        this.racketY = y;
    }

    public int getX() {
        return racketX;
    }

    public int getY() {
        return racketY;
    }

    public int getWidth() {
        return racketWidth;
    }

    public int getHeight() {
        return racketHeight;
    }

    public double getAngle() {
        if (LR) {
            return 360 - angle;
        }
        return angle;
    }
}
