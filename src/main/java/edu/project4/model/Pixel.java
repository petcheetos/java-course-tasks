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
}
