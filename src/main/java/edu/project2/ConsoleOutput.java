package edu.project2;

@SuppressWarnings("RegexpSinglelineJava")
public class ConsoleOutput {
    public static final String INCORRECT_SIZE = "Height and width must be correct";
    public static final String INCORRECT_COORDINATE = "Coordinate must be correct";
    public static final String INCORRECT_TYPE = "Type must be correct";
    public static final String CELL_IS_NOT_PASSAGE = "Cell is not a passage cell";
    public static final String ERROR_INPUT = "Incorrect input";
    public static final String ASK_FOR_SIZE = "Input a height and width. Enter odd numbers";
    public static final String ASK_FOR_COORDINATES =
        "Enter the coordinates: row and column for start point, then for end point";

    public static final String GREETING = """
        Choose the algorithm to generate maze:
        \t--DFS
        \t--Prim
        """;
    public static final String ASK_TO_SOLVE = """
        If you want to solve type <s>.
        Type <g> to generate maze
        Type <quit> to quit
        """;
    public static final String CHOOSE_TO_SOLVE = """
        Choose the algorithm to solve maze:
        \t--DFS
        \t--BFS
        """;

    public static final String PASSAGE_SYMBOL = "◻\uFE0F";
    public static final String WALL_SYMBOL = "⬛";
    public static final String RIGHT_WAY_SYMBOL = "\uD83D\uDD3A";
    public static final String LINE_FEED = "\n";
    public static final String DFS = "DFS";
    public static final String BFS = "BFS";
    public static final String PRIM = "Prim";
    public static final String GENERATE = "g";
    public static final String SOLVE = "s";
    public static final String QUIT = "quit";

    private ConsoleOutput() {
    }

    public static void greet() {
        System.out.println(GREETING);
    }

    public static void askToSolve() {
        System.out.println(ConsoleOutput.ASK_TO_SOLVE);
    }

    public static void askForSize() {
        System.out.println(ConsoleOutput.ASK_FOR_SIZE);
    }

    public static void askForCoordinates() {
        System.out.println(ConsoleOutput.ASK_FOR_COORDINATES);
    }

    public static void chooseToSolve() {
        System.out.println(ConsoleOutput.CHOOSE_TO_SOLVE);
    }

    public static void printErrorInput() {
        System.out.println(ConsoleOutput.ERROR_INPUT);
    }

    public static void print(String string) {
        System.out.println(string);
    }
}
