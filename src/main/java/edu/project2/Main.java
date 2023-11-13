package edu.project2;

import java.util.Scanner;

public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ConsoleMaze consoleMaze = new ConsoleMaze();
            consoleMaze.run(scanner);
        }
    }
}
