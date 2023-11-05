package edu.project2;

import edu.project2.Solvers.BFSMazeSolver;
import edu.project2.Solvers.DFSMazeSolver;
import edu.project2.Solvers.Solver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolversTest {

    @Test
    @DisplayName("Testing DFS solver")
    void testDFSSolver() {
        Maze maze = new Maze(7, 7);
        List<Maze.Coordinate> coors = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (1 <= i && i <= 5) {
                maze.setCellTypeAt(i, 1, Maze.Cell.Type.PASSAGE);
            }
            if (2 <= i && i <= 4) {
                maze.setCellTypeAt(5, i, Maze.Cell.Type.PASSAGE);
                maze.setCellTypeAt(i, 3, Maze.Cell.Type.PASSAGE);
            }
        }
        maze.setCellTypeAt(2, 4, Maze.Cell.Type.PASSAGE);
        maze.setCellTypeAt(3, 4, Maze.Cell.Type.PASSAGE);

        String expected = """
            ⬛⬛⬛⬛⬛⬛⬛
            ⬛🔺⬛⬛⬛⬛⬛
            ⬛🔺⬛🔺◻️⬛⬛
            ⬛🔺⬛🔺◻️⬛⬛
            ⬛🔺⬛🔺⬛⬛⬛
            ⬛🔺🔺🔺◻️⬛⬛
            ⬛⬛⬛⬛⬛⬛⬛
            """;

        Solver solver = new DFSMazeSolver();
        List<Maze.Coordinate> solved = solver.solve(maze, new Maze.Coordinate(1, 1), new Maze.Coordinate(2, 3));
        String actual = Renderer.render(maze, solved);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Testing BFS solver")
    void testBFSSolver() {
        Maze maze = new Maze(7, 7);
        List<Maze.Coordinate> coors = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (1 <= i && i <= 5) {
                maze.setCellTypeAt(i, 1, Maze.Cell.Type.PASSAGE);
            }
            if (2 <= i && i <= 4) {
                maze.setCellTypeAt(5, i, Maze.Cell.Type.PASSAGE);
                maze.setCellTypeAt(i, 3, Maze.Cell.Type.PASSAGE);
            }
        }
        maze.setCellTypeAt(2, 4, Maze.Cell.Type.PASSAGE);
        maze.setCellTypeAt(3, 4, Maze.Cell.Type.PASSAGE);

        String expected = """
            ⬛⬛⬛⬛⬛⬛⬛
            ⬛🔺⬛⬛⬛⬛⬛
            ⬛🔺⬛🔺◻️⬛⬛
            ⬛🔺⬛🔺◻️⬛⬛
            ⬛🔺⬛🔺⬛⬛⬛
            ⬛🔺🔺🔺◻️⬛⬛
            ⬛⬛⬛⬛⬛⬛⬛
            """;

        Solver solver = new BFSMazeSolver();
        List<Maze.Coordinate> solved = solver.solve(maze, new Maze.Coordinate(1, 1), new Maze.Coordinate(2, 3));
        String actual = Renderer.render(maze, solved);

        assertEquals(expected, actual);
    }
}
