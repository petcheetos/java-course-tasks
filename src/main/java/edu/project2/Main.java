package edu.project2;

public class Main {
    public static int[][] maze =  {
        {1, 0, 1, 1, 2},
        {1, 2, 2, 2, 2},
        {1, 2, 1, 0, 1},
        {0, 2, 2, 2, 1},
        {1, 0, 1, 2, 1}
    };

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (maze[i][j] == 1) {
                    //System.out.print("❌");
                    System.out.print("⬛");
                }
                if (maze[i][j] == 2) {
                    System.out.print("\uD83D\uDD39");
                } else if (maze[i][j] == 0) {
                    System.out.print("◻\uFE0F");
                }
                if (j == 4) {
                    System.out.println();
                }
            }
        }
    }
    //стена System.out.print("⬛");
    //проход System.out.print("◻\uFE0F");
    //решение System.out.print("\uD83D\uDD39");
}
