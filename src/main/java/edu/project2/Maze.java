package edu.project2;

public final class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        checkSize(height, width);
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public Maze(int height, int width) {
        checkSize(height, width);
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new Cell(i, j, Cell.Type.WALL);
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public Cell.Type getCellTypeAt(int row, int col) {
        if (row < 0 || col < 0 || row >= height || col >= width) {
            throw new IllegalArgumentException(ConsoleOutput.INCORRECT_COORDINATE);
        }
        return this.grid[row][col].getType();
    }

    public void setCellTypeAt(int row, int col, Cell.Type type) {
        if (row < 0 || col < 0 || row > height || col > width) {
            throw new IllegalArgumentException(ConsoleOutput.INCORRECT_COORDINATE);
        }
        if (type == null) {
            throw new NullPointerException(ConsoleOutput.INCORRECT_TYPE);
        }
        grid[row][col].setType(type);
    }

    private void checkSize(int height, int width) throws IllegalArgumentException {
        if (height <= 2 || width <= 2) {
            throw new IllegalArgumentException(ConsoleOutput.INCORRECT_SIZE);
        }
    }

    public static class Cell {
        public enum Type {
            WALL,
            PASSAGE,
            WAY
        }

        private final int row;
        private final int col;
        private Type type;

        public Cell(int row, int col, Type type) {
            if (row < 0 || col < 0) {
                throw new IllegalArgumentException(ConsoleOutput.INCORRECT_COORDINATE);
            }
            this.row = row;
            this.col = col;
            this.type = type;
        }

        public void setType(Type newType) {
            if (newType != null) {
                type = newType;
            }
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public Type getType() {
            return type;
        }
    }

    public record Coordinate(int row, int col) {
    }
}
