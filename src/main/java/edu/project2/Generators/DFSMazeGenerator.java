package edu.project2.Generators;

import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class DFSMazeGenerator implements Generator {
    private final Random random = new Random();
    private static final int[][] DIRECTIONS = new int[][] {
        {-2, 0}, {2, 0}, {0, -2}, {0, 2}
    };

    @Override
    public Maze generate(int height, int width) {
        Stack<Maze.Coordinate> stack = new Stack<>();
        boolean[][] visited = new boolean[height][width];
        Maze maze = new Maze(height, width);
        for (int row = 1; row < height - 1; row += 2) {
            for (int col = 1; col < width - 1; col += 2) {
                maze.setCellTypeAt(row, col, Maze.Cell.Type.PASSAGE);
            }
        }
        stack.add(new Maze.Coordinate(1, 1));
        while (!stack.isEmpty()) {
            Maze.Coordinate currCoordinate = stack.getLast();
            visited[currCoordinate.row()][currCoordinate.col()] = true;
            List<int[]> neighbors = new ArrayList<>();
            for (int[] step : DIRECTIONS) {
                int currRow = currCoordinate.row() + step[0];
                int currCol = currCoordinate.col() + step[1];
                if (currCol >= 0 && currRow >= 0 && currCol < width && currRow < height
                    && !visited[currRow][currCol]) {
                    neighbors.add(step);
                }
            }
            if (!neighbors.isEmpty()) {
                int[] nextStep = neighbors.get(random.nextInt(neighbors.size()));
                int newRow = currCoordinate.row() + nextStep[0] / 2;
                int newCol = currCoordinate.col() + nextStep[1] / 2;
                maze.setCellTypeAt(newRow, newCol, Maze.Cell.Type.PASSAGE);
                stack.push(new Maze.Coordinate(
                    currCoordinate.row() + nextStep[0],
                    currCoordinate.col() + nextStep[1]
                ));
            } else {
                stack.pop();
            }
        }
        return maze;
    }
}

