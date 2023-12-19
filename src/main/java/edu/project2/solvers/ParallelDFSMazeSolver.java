package edu.project2.solvers;

import edu.project2.ConsoleOutput;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ParallelDFSMazeSolver implements Solver {
    private static final int[][] DIRECTIONS = new int[][]{
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    @Override
    public List<Maze.Coordinate> solve(Maze maze, Maze.Coordinate start, Maze.Coordinate end) {
        if (maze.getCellTypeAt(start.row(), start.col()) != Maze.Cell.Type.PASSAGE
            || maze.getCellTypeAt(end.row(), end.col()) != Maze.Cell.Type.PASSAGE) {
            throw new IllegalArgumentException(ConsoleOutput.CELL_IS_NOT_PASSAGE);
        }
        List<Future<List<Maze.Coordinate>>> futures = new ArrayList<>();
        try (ExecutorService executorService = Executors.newFixedThreadPool(2);) {
            for (int[] step : DIRECTIONS) {
                Future<List<Maze.Coordinate>> future = executorService.submit(() -> {
                    List<Maze.Coordinate> path = new ArrayList<>();
                    boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];
                    findPath(maze, new Maze.Coordinate(start.row() + step[0], start.col() + step[1]), end, visited, path);
                    return path;
                });
                futures.add(future);
            }
        }
        for (Future<List<Maze.Coordinate>> future : futures) {
            try {
                List<Maze.Coordinate> path = future.get();
                if (!path.isEmpty() && path.get(path.size() - 1).equals(end)) {
                    return path;
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException();
            }
        }
        return null;
    }

    private void findPath(
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
            return;
        }
        visited[current.row()][current.col()] = true;
        path.add(current);
        if (!current.equals(end)) {
            for (int[] step : DIRECTIONS) {
                int newRow = current.row() + step[0];
                int newCol = current.col() + step[1];

                findPath(maze, new Maze.Coordinate(newRow, newCol), end, visited, path);
            }
            if (!path.isEmpty() && !path.get(path.size() - 1).equals(end)) {
                path.remove(path.size() - 1);
            }
        }
    }
}
