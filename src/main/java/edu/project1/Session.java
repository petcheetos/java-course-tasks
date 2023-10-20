package edu.project1;

public final class Session {
    private static String hiddenWord;
    public GameStatus status = GameStatus.Default;

    public Session() {
        hiddenWord = Dictionary.choseRandomWord();
    }

    public Session(String string) {
        hiddenWord = string;
    }

    public void run() {
        GameExecutor game = new GameExecutor(hiddenWord);
        status = game.play();
    }
}
