import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.geom.AffineTransform;

public class VisualResource {
    public BufferedImage[] playerImage;
    public BufferedImage backGroundImage;

    public VisualResource(boolean player, boolean LR) {
        try {
            playerImage = new BufferedImage[7];
            // moveFrame
            for (int i = 0; i < 4; i++) {
                if (!LR) {
                    playerImage[i] = ImageIO
                            .read(getClass().getResource("/images/ezgif-split/move" + (i + 1) + ".png"));
                } else {
                    playerImage[i] = ImageIO
                            .read(getClass().getResource("/images/ezgif-split_Red/move" + (i + 1) + ".png"));
                    playerImage[i] = flipImageHorizontally(playerImage[i]);
                }
            }
            // standFrame
            if (!LR) {
                playerImage[4] = ImageIO
                        .read(getClass().getResource("/images/ezgif-split/stand1.png"));
            } else {
                playerImage[4] = ImageIO
                        .read(getClass().getResource("/images/ezgif-split_Red/stand1.png"));
                playerImage[4] = flipImageHorizontally(playerImage[4]);
            }
            // jumpFrame
            if (!LR) {
                playerImage[5] = ImageIO
                        .read(getClass().getResource("/images/ezgif-split/Jump1.png"));
            } else {
                playerImage[5] = ImageIO
                        .read(getClass().getResource("/images/ezgif-split_Red/Jump1.png"));
                playerImage[5] = flipImageHorizontally(playerImage[5]);
            }
            // swing
            if (!LR) {
                playerImage[6] = ImageIO
                        .read(getClass().getResource("/images/ezgif-split/swing1.png"));
            } else {
                playerImage[6] = ImageIO
                        .read(getClass().getResource("/images/ezgif-split_Red/swing1.png"));
                playerImage[6] = flipImageHorizontally(playerImage[5]);
            }
        } catch (IOException e) {
            System.out.println("Cant Find Image File Path");
            e.printStackTrace();
        }
    }

    public VisualResource(boolean player) {
        try {
            backGroundImage = ImageIO
                    .read(getClass().getResource("/images/NFUBed.jpg"));
        } catch (IOException e) {
            System.out.println("Cant Find Image File Path");
            e.printStackTrace();
        }
    }

    public BufferedImage flipImageHorizontally(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();

        BufferedImage flipped = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = flipped.createGraphics();

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-w, 0);
        g.drawImage(image, tx, null);

        g.dispose();
        return flipped;
    }

}
