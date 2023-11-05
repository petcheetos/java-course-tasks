package edu.project2;

import edu.project2.Solvers.BFSMazeSolver;
import edu.project2.Solvers.DFSMazeSolver;
import edu.project2.Solvers.Solver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleMazeTest {

    @Test
    @DisplayName("Testing output with incorrect user input")
    void testOutputWithIncorrectInput() throws Exception {
        withTextFromSystemIn("abs\n", "quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    Main.main(new String[] {});
                });
                assertThat(output)
                    .contains(ConsoleOutput.ERROR_INPUT);
            });
    }

    @Test
    void testOutputWithIncorrectInput_2() throws Exception {
        withTextFromSystemIn("Prim\n", "7\n", "7\n", "abs\n", "quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    Main.main(new String[] {});
                });
                assertThat(output)
                    .contains(ConsoleOutput.ERROR_INPUT);
            });
    }

    @Test
    @DisplayName("Testing maze constructor with incorrect values")
    void testMazeConstructorThrowsExceptions() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(-1, 7, new Maze.Cell[][] {});
            }
        );
    }

    @Test
    @DisplayName("Testing getCellType with incorrect parameters")
    void testMazeGetCellTypeThrowsExceptions() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                maze.getCellTypeAt(-2, 4);
            }
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                maze.getCellTypeAt(7, 4);
            }
        );
    }

    @Test
    @DisplayName("Testing setCellType with incorrect parameters")
    void testMazeSetCellTypeThrowsExceptions() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                maze.setCellTypeAt(-2, 4, Maze.Cell.Type.WALL);
            }
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                maze.setCellTypeAt(7, 4, Maze.Cell.Type.WALL);
            }
        );

        assertThrows(
            NullPointerException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                maze.setCellTypeAt(3, 3, null);
            }
        );
    }

    @Test
    @DisplayName("Testing DFS solver and BFS solver with incorrect Cells")
    void testSolversThrowsExceptions() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                Solver solver = new BFSMazeSolver();
                solver.solve(maze, new Maze.Coordinate(-1, 2), new Maze.Coordinate(3, 3));
            }
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                Maze maze = new Maze(5, 5);
                Solver solver = new DFSMazeSolver();
                solver.solve(maze, new Maze.Coordinate(6, 2), new Maze.Coordinate(3, 3));
            }
        );
    }
}
