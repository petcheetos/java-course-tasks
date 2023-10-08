package edu.hw1.task8;

public final class ChessKnight {
    public static final int BOARD_LINES = 8;
    public static final int BOARD_COLUMNS = 8;
    public static final int[][] MOVES = {{-1, -2}, {-1, 2}, {-2, -1}, {-2, 1}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    private ChessKnight() {
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < BOARD_LINES; i++) {
            for (int j = 0; j < BOARD_COLUMNS; j++) {
                if (board[i][j] == 1) {
                    for (int[] move : MOVES) {
                        if ((i + move[0] > 0) && (j + move[1] > 0) && (i + move[0] < BOARD_LINES)
                            && (j + move[1] < BOARD_COLUMNS)) {
                            if (board[i + move[0]][j + move[1]] == 1) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
