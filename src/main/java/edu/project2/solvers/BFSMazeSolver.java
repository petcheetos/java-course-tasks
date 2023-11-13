package edu.project2.solvers;

import edu.project2.ConsoleOutput;
import edu.project2.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSMazeSolver implements Solver {
    private static final int[][] DIRECTIONS = new int[][] {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    @Override
    public List<Maze.Coordinate> solve(Maze maze, Maze.Coordinate start, Maze.Coordinate end) {
        if (maze.getCellTypeAt(start.row(), start.col()) != Maze.Cell.Type.PASSAGE
            || maze.getCellTypeAt(end.row(), end.col()) != Maze.Cell.Type.PASSAGE) {
            throw new IllegalArgumentException(ConsoleOutput.CELL_IS_NOT_PASSAGE);
        }
        Queue<Maze.Coordinate> queue = new LinkedList<>();
        Map<Maze.Coordinate, Maze.Coordinate> parentMap = new HashMap<>();
        boolean[][] visited = new boolean[maze.getHeight()][maze.getWidth()];

        queue.add(start);
        visited[start.row()][start.col()] = true;

        while (!queue.isEmpty()) {
            Maze.Coordinate current = queue.poll();
            if (current.equals(end)) {
                return reconstructPath(parentMap, start, end);
            }
            for (Maze.Coordinate neighbor : getUnvisitedNeighbors(current, maze, visited)) {
                queue.add(neighbor);
                visited[neighbor.row()][neighbor.col()] = true;
                parentMap.put(neighbor, current);
            }
        }
        return null;
    }

    private List<Maze.Coordinate> getUnvisitedNeighbors(Maze.Coordinate current, Maze maze, boolean[][] visited) {
        List<Maze.Coordinate> neighbors = new ArrayList<>();
        for (int[] step : DIRECTIONS) {
            int newRow = current.row() + step[0];
            int newCol = current.col() + step[1];
            if ((newRow >= 0 && newRow < maze.getHeight() && newCol >= 0 && newCol < maze.getWidth())
                && !visited[newRow][newCol]
                && maze.getCellTypeAt(newRow, newCol) == Maze.Cell.Type.PASSAGE) {
                neighbors.add(new Maze.Coordinate(newRow, newCol));
            }
        }
        return neighbors;
    }

    private List<Maze.Coordinate> reconstructPath(
        Map<Maze.Coordinate, Maze.Coordinate> parentMap,
        Maze.Coordinate start,
        Maze.Coordinate end
    ) {
        List<Maze.Coordinate> path = new ArrayList<>();
        Maze.Coordinate current = end;
        while (!current.equals(start)) {
            path.add(current);
            current = parentMap.get(current);
        }
        path.add(start);;
        path.reversed();
        return path;
    }
}
