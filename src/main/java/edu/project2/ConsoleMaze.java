package edu.project2;

import edu.project2.Generators.DFSMazeGenerator;
import edu.project2.Generators.Generator;
import edu.project2.Generators.PrimMazeGenerator;
import edu.project2.Solvers.BFSMazeSolver;
import edu.project2.Solvers.DFSMazeSolver;
import edu.project2.Solvers.Solver;
import java.util.List;
import java.util.Scanner;

public class ConsoleMaze {
    private static final Scanner SCANNER = new Scanner(System.in);

    public void run() {
        while (true) {
            ConsoleOutput.greet();
            try {
                String inputStr = inputString();
                if (inputStr.equals(ConsoleOutput.QUIT)) {
                    break;
                }
                Maze maze = executeGeneration(inputStr);
                if (maze != null) {
                    ConsoleOutput.askToSolve();
                    inputStr = inputString();
                    if (inputStr.equals(ConsoleOutput.QUIT)) {
                        break;
                    }
                    executeSolve(inputStr, maze);
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private String inputString() {
        return ConsoleMaze.SCANNER.next();
    }

    private int inputInt() {
        return ConsoleMaze.SCANNER.nextInt();
    }

    private Maze executeGeneration(String string) {
        Maze maze = null;
        if (string.equals(ConsoleOutput.DFS)) {
            Generator generator = new DFSMazeGenerator();
            ConsoleOutput.askForSize();
            maze = generator.generate(inputInt(), inputInt());
            ConsoleOutput.print(Renderer.render(maze));
        } else if (string.equals(ConsoleOutput.PRIM)) {
            Generator generator = new PrimMazeGenerator();
            ConsoleOutput.askForSize();
            maze = generator.generate(inputInt(), inputInt());
            ConsoleOutput.print(Renderer.render(maze));
        } else {
            ConsoleOutput.printErrorInput();
        }
        return maze;
    }

    private void executeSolve(String string, Maze maze) {
        if (string.equals(ConsoleOutput.SOLVE)) {
            ConsoleOutput.askForCoordinates();
            int rowStart = inputInt();
            int colStart = inputInt();
            int rowEnd = inputInt();
            int colEnd = inputInt();
            ConsoleOutput.chooseToSolve();
            String alg = inputString();
            if (alg.equals(ConsoleOutput.BFS)) {
                Solver solver = new BFSMazeSolver();
                List<Maze.Coordinate> list = solver.solve(
                    maze,
                    new Maze.Coordinate(rowStart - 1, colStart - 1),
                    new Maze.Coordinate(rowEnd - 1, colEnd - 1)
                );
                ConsoleOutput.print(Renderer.render(maze, list));
            } else if (alg.equals(ConsoleOutput.DFS)) {
                Solver solver = new DFSMazeSolver();
                List<Maze.Coordinate> list = solver.solve(
                    maze,
                    new Maze.Coordinate(rowStart - 1, colStart - 1),
                    new Maze.Coordinate(rowEnd - 1, colEnd - 1)
                );
                ConsoleOutput.print(Renderer.render(maze, list));
            }
        } else if (!string.equals(ConsoleOutput.GENERATE)) {
            ConsoleOutput.printErrorInput();
        }
    }
}

