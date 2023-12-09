package edu.project4;

import java.util.Scanner;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleManager {
    public static final String VARIANTS =
        "1 - Cross\n2 - Cylinder\n3 - Disc\n4 - Ex\n5 - Handkerchief\n6 - Heart\n7 - Spherical\n8 - Tangent";
    public static final String SPACE = " ";
    public static final String INVALID_VALUES = "Invalid values\n";

    private ConsoleManager() {
    }

    public static void choseTransformation() {
        System.out.println("\nType space-separated numbers of transformation:\n" + VARIANTS);
    }

    public static int inputSamples(Scanner scanner) {
        System.out.println("\nInput number of samples:\n");
        return scanner.nextInt();
    }

    public static int inputIterPerSample(Scanner scanner) {
        System.out.println("\nInput number of iterations per samples:\n");
        return scanner.nextInt();
    }

    public static int inputSymmetry(Scanner scanner) {
        System.out.println("\nInput value of symmetry:\n");
        return scanner.nextInt();
    }

    public static int inputNumberOfThreads(Scanner scanner) {
        System.out.println("\nInput number of threads:\n");
        return scanner.nextInt();
    }

    public static int inputHeight(Scanner scanner) {
        System.out.println("\nInput height\n");
        return scanner.nextInt();
    }

    public static int inputWidth(Scanner scanner) {
        System.out.println("\nInput width\n");
        return scanner.nextInt();
    }

    public static String inputFormat(Scanner scanner) {
        System.out.println("\nInput format\n");
        return scanner.next();
    }
}
