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
        if (string.equals(ConsoleOutput.DFS)) {
            Generator generator = new DFSMazeGenerator();
            ConsoleOutput.askForSize();
            maze = generator.generate(inputInt(scanner), inputInt(scanner));
            ConsoleOutput.print(Renderer.render(maze));
        } else if (string.equals(ConsoleOutput.PRIM)) {
            Generator generator = new PrimMazeGenerator();
            ConsoleOutput.askForSize();
            maze = generator.generate(inputInt(scanner), inputInt(scanner));
            ConsoleOutput.print(Renderer.render(maze));
        } else {
            ConsoleOutput.printErrorInput();
        }
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

