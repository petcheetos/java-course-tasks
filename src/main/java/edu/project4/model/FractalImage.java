package edu.project4.model;

import java.awt.Color;

public class FractalImage {

    private final Pixel[][] pixels;
    private final int width;
    private final int height;

    private FractalImage(Pixel[][] pixels, int width, int height) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
    }

    public static FractalImage create(int width, int height) {
        Pixel[][] pixels = new Pixel[width][height];
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j] = new Pixel(Color.black, 0);
            }
        }
        return new FractalImage(pixels, width, height);
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel getPixel(int x, int y) {
        if (0 <= x && x < width && 0 <= y && y < height) {
            return pixels[x][y];
        }
        return null;
    }
}
