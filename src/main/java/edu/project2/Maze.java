package edu.project2;

public final class Maze {
    private final int height;
    private final int width;
    public final Cell[][] grid; //потом сделать private

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }
}
