package edu.project4.process;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {

    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, String format) throws IOException {
        ImageIO.write(createImage(image.getPixels()), format, filename.toFile());
    }

    private static BufferedImage createImage(Pixel[][] pixels) {
        int width = pixels.length;
        int height = pixels[0].length;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedImage.setRGB(x, y, pixels[x][y].getColor().getRGB());
            }
        }
        return bufferedImage;
    }
}
