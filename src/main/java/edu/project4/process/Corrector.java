package edu.project4.process;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;

public class Corrector {
    public final double gamma = 2.2;

    public void process(FractalImage image) {
        double max = 0.0;
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                if (pixel.getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                    max = Math.max(max, pixel.getNormal());
                }
            }
        }
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Pixel pixel = image.getPixel(x, y);
                pixel.setNormal(pixel.getNormal() / max);
                pixel.setColor(
                    (int) (pixel.getColor().getRed() * Math.pow(pixel.getNormal(), (1.0 / gamma))),
                    (int) (pixel.getColor().getGreen() * Math.pow(pixel.getNormal(), (1.0 / gamma))),
                    (int) (pixel.getColor().getBlue() * Math.pow(pixel.getNormal(), (1.0 / gamma)))
                );
            }
        }
    }
}
