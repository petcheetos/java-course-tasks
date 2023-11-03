package edu.project2;

public record Cell(int row, int col, Type type) { //row - строка
    public enum Type {
        WALL,
        PASSAGE,
        WAY,
        ENTRANCE
    }

}
