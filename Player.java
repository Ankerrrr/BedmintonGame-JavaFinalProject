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

public class Player extends JPanel {
    // private Non-number Verable
    private Window window;
    private Net net;
    private Racket racket;
    private UI ui;
    private VisualResource visualResource;
    private VisualResource visualresoNotPlayer;
    Sound sound;
    Timer slowDownTimer;
    Timer frameLoopTimer;
    Timer jumpTimer;
    Timer moveSoundTimer;

    // private verable
    private int playerX, playerY;
    private boolean LR;
    float nowSpeed = 0;
    private boolean LLbeenClick;
    private boolean LRbeenClick;
    private boolean RLbeenClick;
    private boolean RRbeenClick;
    private boolean LJumping;
    private boolean RJumping;
    private int photoIndex = 0;
    private boolean goingUp;
    private boolean LupSwing;
    private boolean RupSwing;
    private boolean LdownSwing;
    private boolean RdownSwing;

    // const verable
    private static final int width = 150;
    private static final int height = 230;
    final int speed = 4;
    final int standFrameIndex = 4;
    final int jumpFrameIndex = 5;
    final int jumpPeakY = 200;

    protected class Mykey {
        protected static final int player1Left = KeyEvent.VK_A;
        protected static final int player1Right = KeyEvent.VK_D;
        protected static final int player1Jump = KeyEvent.VK_SPACE;
        protected static final int player1UpSwing = KeyEvent.VK_CONTROL;
        protected static final int player1DownSwing = KeyEvent.VK_SHIFT;

        protected static final int player2Left = KeyEvent.VK_LEFT;
        protected static final int player2Right = KeyEvent.VK_RIGHT;
        protected static final int player2Jump = KeyEvent.VK_UP;
        protected static final int player2UpSwing = KeyEvent.VK_P;
        protected static final int player2DownSwing = KeyEvent.VK_O;

        protected static final int startGame = KeyEvent.VK_ENTER;
        protected static final int Info = KeyEvent.VK_I;
    }

    // Function
    public Player(Window w, Net net, boolean LR, Sound sound, UI ui) {
        racket = new Racket(LR);
        visualResource = new VisualResource(true, LR);
        visualresoNotPlayer = new VisualResource(false);
        this.window = w;
        this.net = net;
        this.LR = LR;
        this.sound = sound;
        this.ui = ui;
        playerY = window.getHeight() - height;

        if (this.LR == false) {
            playerX = 200;
        } else {
            playerX = 600;
        }

    }

    public void paint(Graphics2D g) {
        // people
        AffineTransform oldTransform = g.getTransform();

        g.drawImage(visualResource.playerImage[photoIndex], playerX, playerY, width, height, this);

        // racket
        int centerX = racket.getX() + racket.getWidth() / 2;
        int centerY = racket.getY() + racket.getHeight() / 2;

        g.rotate(Math.toRadians(racket.getAngle()), centerX, centerY + 80); // 80 is Good

        g.setColor(Color.WHITE);
        g.drawImage(visualresoNotPlayer.racket[racket.getRacketImageIndex()], racket.getX(), racket.getY(),
                racket.getWidth(),
                racket.getHeight(),
                this);

        g.setTransform(oldTransform);
    }

    private void changeFrame(int n, boolean loop) {
        if (!loop) {
            photoIndex = n;
            if (frameLoopTimer != null) {
                frameLoopTimer.stop();
            }
        } else {
            if (frameLoopTimer != null && frameLoopTimer.isRunning()) {
                return;
            }
            frameLoopTimer = new Timer(100, e -> {
                photoIndex++;
                if (photoIndex >= standFrameIndex) {
                    photoIndex = 0;
                }
            });
            frameLoopTimer.start();
        }
    }

    private void jumping() {
        if (jumpTimer != null && jumpTimer.isRunning()) {
            return;
        }
        goingUp = true;
        jumpTimer = new Timer(20, e -> {
            if (goingUp) {
                playerY -= 10;
                if (playerY <= jumpPeakY) {
                    goingUp = false;
                }
            } else {
                playerY += 10;
                if (playerY >= window.getHeight() - height) {
                    playerY = window.getHeight() - height;
                    if (!LR) {
                        LJumping = false;
                    } else {
                        RJumping = false;
                    }
                    jumpTimer.stop();
                }
                // changeFrame(4, false);
            }
        });
        jumpTimer.start();
    }

    public void playerMove() {
        // racket
        if ((!LR && LupSwing) || (LR && RupSwing)) {
            if (!LR) {
                LupSwing = racket.swing(true);
            } else {
                RupSwing = racket.swing(true);
            }
        }
        if ((!LR && LdownSwing) || (LR && RdownSwing)) {
            if (!LR) {
                LdownSwing = racket.swing(false);
            } else {
                RdownSwing = racket.swing(false);
            }
        }
        racket.updatePoistion(playerX, playerY, width, height);

        // player
        if (!LR && LJumping || LR && RJumping) {
            jumping();
            changeFrame(jumpFrameIndex, false);
        } else if (nowSpeed == 0) {
            changeFrame(standFrameIndex, false);
        }
        if (nowSpeed != 0) {
            changeFrame(0, true);
            moveSound();
        }
        if (!LR) { // player1
            if (LLbeenClick && !LRbeenClick) {
                if (playerX <= 0) {
                    return;
                }
                nowSpeed = -speed;
            } else if (LRbeenClick && !LLbeenClick) {
                if (playerX + width >= window.getWidth() / 2) {
                    return;
                }
                nowSpeed = speed;
            } else {
                if (nowSpeed != 0) {
                    slowDown();
                }
            }
        } else { // player2
            if (RLbeenClick && !RRbeenClick) {
                if (playerX <= (window.getWidth() / 2) - net.getWidth()) {
                    return;
                }
                nowSpeed = -speed;
            } else if (RRbeenClick && !RLbeenClick) {
                if (playerX >= window.getWidth() - width) {
                    return;
                }
                nowSpeed = speed;
            } else {
                if (nowSpeed != 0) {
                    slowDown();
                }
            }
        }
        playerX += nowSpeed;

    }

    private void moveSound() {
        if (moveSoundTimer != null && moveSoundTimer.isRunning()) {
            return;
        }
        moveSoundTimer = new Timer(400, e -> {
            moveSoundTimer.stop();
        });
        sound.playMove();
        moveSoundTimer.start();
    }

    private void slowDown() {
        if (slowDownTimer != null && slowDownTimer.isRunning()) {
            return;
        }
        slowDownTimer = new Timer(16, e -> {
            if (nowSpeed > 0) {
                nowSpeed -= 0.5;
            } else if (nowSpeed < 0) {
                nowSpeed += 0.5;
            }

            if (nowSpeed == 0) {
                sound.playjeje();
                slowDownTimer.stop();
            }
        });
        slowDownTimer.start();
    }

    public void keyPress(KeyEvent k) {
        if (window.gameState == window.gameState.PLAYING) {
            if (k.getKeyCode() == Mykey.player1Left && !LR) {
                LLbeenClick = true; // move
            } else if (k.getKeyCode() == Mykey.player1Right && !LR) {
                LRbeenClick = true;
            } else if (k.getKeyCode() == Mykey.player2Left && LR) {
                RLbeenClick = true;
            } else if (k.getKeyCode() == Mykey.player2Right && LR) {
                RRbeenClick = true;
            } else if (k.getKeyCode() == Mykey.player1Jump && !LR) {
                LJumping = true; // jump
            } else if (k.getKeyCode() == Mykey.player2Jump && LR) {
                RJumping = true;
            } else if (k.getKeyCode() == Mykey.player1UpSwing && !LR) {
                LupSwing = true;// swing
            } else if (k.getKeyCode() == Mykey.player2UpSwing && LR) {
                RupSwing = true;
            } else if (k.getKeyCode() == Mykey.player1DownSwing && !LR) {
                LdownSwing = true;// swing
            } else if (k.getKeyCode() == Mykey.player2DownSwing && LR) {
                RdownSwing = true;
            }
        } else if (window.gameState == window.gameState.START && !LR) {
            if (k.getKeyCode() == Mykey.startGame) {
                sound.playStartGame();
                window.gameState = window.gameState.PLAYING;
            } else if (k.getKeyCode() == Mykey.Info) {
                ui.toggleInfo();
            }
        }
    }

    public void keyReleased(KeyEvent k) {
        if (k.getKeyCode() == Mykey.player1Left && !LR) {
            LLbeenClick = false;
        } else if (k.getKeyCode() == Mykey.player1Right && !LR) {
            LRbeenClick = false;
        } else if (k.getKeyCode() == Mykey.player2Left && LR) {
            RLbeenClick = false;
        } else if (k.getKeyCode() == Mykey.player2Right && LR) {
            RRbeenClick = false;
        }
    }

    public Racket getRacket() {
        return racket;
    }
}
