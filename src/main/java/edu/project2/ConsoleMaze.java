package edu.project2;

import edu.project2.generators.DFSMazeGenerator;
import edu.project2.generators.Generator;
import edu.project2.generators.PrimMazeGenerator;
import edu.project2.solvers.BFSMazeSolver;
import edu.project2.solvers.DFSMazeSolver;
import edu.project2.solvers.Solver;
import java.util.List;
import java.util.Scanner;

public class ConsoleMaze {

    public void run(Scanner scanner) {
        while (true) {
            ConsoleOutput.greet();
            try {
                String inputStr = inputString(scanner);
                if (inputStr.equals(ConsoleOutput.QUIT)) {
                    break;
                }
                Maze maze = executeGeneration(inputStr, scanner);
                if (maze != null) {
                    ConsoleOutput.askToSolve();
                    inputStr = inputString(scanner);
                    if (inputStr.equals(ConsoleOutput.QUIT)) {
                        break;
                    }
                    executeSolve(inputStr, maze, scanner);
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private String inputString(Scanner scanner) {
        return scanner.next();
    }

    private int inputInt(Scanner scanner) {
        return scanner.nextInt();
    }

    private Maze executeGeneration(String string, Scanner scanner) {
        Maze maze = null;
        Generator generator = switch (string) {
            case ConsoleOutput.DFS -> new DFSMazeGenerator();
            case ConsoleOutput.PRIM -> new PrimMazeGenerator();
            default -> null;
        };
        if (generator == null) {
            ConsoleOutput.printErrorInput();
            return null;
        }
        ConsoleOutput.askForSize();
        maze = generator.generate(inputInt(scanner), inputInt(scanner));
        ConsoleOutput.print(Renderer.render(maze));
        return maze;
    }

    private void executeSolve(String string, Maze maze, Scanner scanner) {
        if (string.equals(ConsoleOutput.SOLVE)) {
            ConsoleOutput.askForCoordinates();
            int rowStart = inputInt(scanner);
            int colStart = inputInt(scanner);
            int rowEnd = inputInt(scanner);
            int colEnd = inputInt(scanner);
            ConsoleOutput.chooseToSolve();
            String alg = inputString(scanner);
            Solver solver = switch (alg) {
                case ConsoleOutput.BFS -> new BFSMazeSolver();
                case ConsoleOutput.DFS -> new DFSMazeSolver();
                default -> null;
            };
            if (solver == null) {
                ConsoleOutput.printErrorInput();
                return;
            }
            List<Maze.Coordinate> list = solver.solve(
                maze,
                new Maze.Coordinate(rowStart - 1, colStart - 1),
                new Maze.Coordinate(rowEnd - 1, colEnd - 1)
            );
            ConsoleOutput.print(Renderer.render(maze, list));
        } else if (!string.equals(ConsoleOutput.GENERATE)) {
            ConsoleOutput.printErrorInput();
        }
    }
}

