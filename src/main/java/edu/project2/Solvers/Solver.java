package edu.project2.Solvers;

import edu.project2.Maze;
import java.util.List;

public interface Solver {
    List<Maze.Coordinate> solve(Maze maze, Maze.Coordinate start, Maze.Coordinate end);
}
