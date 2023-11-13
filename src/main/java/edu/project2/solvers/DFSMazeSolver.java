package edu.project2.solvers;

import edu.project2.ConsoleOutput;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;

public class DFSMazeSolver implements Solver {
    private static final int[][] DIRECTIONS = new int[][] {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    @Override
    public List<Maze.Coordinate> solve(Maze maze, Maze.Coordinate start, Maze.Coordinate end) {
        if (maze.getCellTypeAt(start.row(), start.col()) != Maze.Cell.Type.PASSAGE
            || maze.getCellTypeAt(end.row(), end.col()) != Maze.Cell.Type.PASSAGE) {
            throw new IllegalArgumentException(ConsoleOutput.CELL_IS_NOT_PASSAGE);
        }
        List<Maze.Coordinate> path = new ArrayList<>();
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];

        if (findPath(maze, start, end, visited, path)) {
            return path;
        } else {
            return null;
        }
    }

    private boolean findPath(
        Maze maze,
        Maze.Coordinate current,
        Maze.Coordinate end,
        boolean[][] visited,
        List<Maze.Coordinate> path
    ) {
        if (!(current.row() >= 0 && current.row() < maze.getHeight() && current.col() >= 0
            && current.col() < maze.getWidth()
            && maze.getCellTypeAt(current.row(), current.col()) == Maze.Cell.Type.PASSAGE)
            || visited[current.row()][current.col()]) {
            return false;
        }

        visited[current.row()][current.col()] = true;
        path.add(current);
        if (current.equals(end)) {
            return true;
        }

        for (int[] step : DIRECTIONS) {
            int newRow = current.row() + step[0];
            int newCol = current.col() + step[1];

            if (findPath(maze, new Maze.Coordinate(newRow, newCol), end, visited, path)) {
                return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }
}
