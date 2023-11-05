package edu.project2;

import java.util.List;

public class Renderer {

    private Renderer() {}

    public static String render(Maze maze) {
        return mazeToString(maze, false);
    }

    public static String render(Maze maze, List<Maze.Coordinate> path) {
        path.forEach(cell -> {
            maze.setCellTypeAt(cell.row(), cell.col(), Maze.Cell.Type.WAY);
        });
        return mazeToString(maze, true);
    }

    private static String mazeToString(Maze maze, boolean isSolved) {
        StringBuilder stringBuilder = new StringBuilder();
        Maze.Cell[][] grid = maze.getGrid();
        for (int row = 0; row < maze.getHeight(); row++) {
            for (int col = 0; col < maze.getWidth(); col++) {
                if (grid[row][col].getType() == Maze.Cell.Type.WALL) {
                    stringBuilder.append(ConsoleOutput.WALL_SYMBOL);
                } else if (grid[row][col].getType() == Maze.Cell.Type.PASSAGE) {
                    stringBuilder.append(ConsoleOutput.PASSAGE_SYMBOL);
                } else if (isSolved && grid[row][col].getType() == Maze.Cell.Type.WAY) {
                    stringBuilder.append(ConsoleOutput.RIGHT_WAY_SYMBOL);
                }
                if (col == maze.getWidth() - 1) {
                    stringBuilder.append(ConsoleOutput.LINE_FEED);
                }
            }
        }
        return stringBuilder.toString();
    }
}
