package edu.project4.model;

import java.awt.Color;

public class Pixel {
    private Color color;
    private int hitCount;
    private double normal;

    public Pixel(Color color, int hitCount) {
        this.color = color;
        this.hitCount = hitCount;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public Color getColor() {
        return color;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void addHit() {
        hitCount++;
    }

    public void setColor(int r, int g, int b) {
        color = new Color(r, g, b);
    }

    public double getNormal() {
        return normal;
    }

    public synchronized void changeColor(Color coefficientColor) {
        if (hitCount == 0) {
            setColor(coefficientColor.getRed(), coefficientColor.getGreen(), coefficientColor.getBlue());
        } else {
            int red = (color.getRed() + coefficientColor.getRed()) / 2;
            int green = (color.getGreen() + coefficientColor.getGreen()) / 2;
            int blue = (color.getBlue() + coefficientColor.getBlue()) / 2;
            color = new Color(red, green, blue);
            hitCount++;
        }
    }
}
