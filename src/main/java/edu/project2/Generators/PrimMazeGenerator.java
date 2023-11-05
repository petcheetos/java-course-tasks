package edu.project2.Generators;

import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimMazeGenerator implements Generator {
    private final Random random = new Random();
    private static final int[][] DIRECTIONS = new int[][] {
        {-2, 0}, {2, 0}, {0, -2}, {0, 2}
    };

    @Override
    public Maze generate(int height, int width) {
        Maze maze = new Maze(height, width);
        List<Maze.Coordinate> coordinatesToCheck = new ArrayList<>();
        int startRow = random.nextInt((height - 1) / 2) * 2 + 1;
        int startCol = random.nextInt((width - 1) / 2) * 2 + 1;
        maze.setCellTypeAt(startRow, startCol, Maze.Cell.Type.PASSAGE);

        for (int[] step : DIRECTIONS) {
            int nextRow = startRow + step[0];
            int nextCol = startCol + step[1];
            if (nextRow >= 0 && nextCol >= 0 && nextRow < height && nextCol < width) {
                coordinatesToCheck.add(new Maze.Coordinate(nextRow, nextCol));
            }
        }

        while (!coordinatesToCheck.isEmpty()) {
            Maze.Coordinate coordinate = coordinatesToCheck.remove(random.nextInt(coordinatesToCheck.size()));
            if (maze.getCellTypeAt(coordinate.row(), coordinate.col()) == Maze.Cell.Type.PASSAGE) {
                continue;
            }

            int currRow = coordinate.row();
            int currCol = coordinate.col();
            maze.setCellTypeAt(currRow, currCol, Maze.Cell.Type.PASSAGE);

            List<Maze.Coordinate> neighbors = new ArrayList<>();
            for (int[] step : DIRECTIONS) {
                int nearRow = currRow + step[0];
                int nearCol = currCol + step[1];
                if (nearRow >= 0 && nearCol >= 0 && nearRow < height && nearCol < width) {
                    if (maze.getCellTypeAt(nearRow, nearCol) == Maze.Cell.Type.WALL) {
                        coordinatesToCheck.add(new Maze.Coordinate(nearRow, nearCol));
                    } else {
                        neighbors.add(new Maze.Coordinate(currRow + (step[0] / 2), currCol + (step[1] / 2)));
                    }
                }
            }
            Maze.Coordinate cord = neighbors.get(random.nextInt(neighbors.size()));
            maze.setCellTypeAt(cord.row(), cord.col(), Maze.Cell.Type.PASSAGE);
        }
        return maze;
    }
}

