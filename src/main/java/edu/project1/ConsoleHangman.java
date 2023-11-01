package edu.project1;

import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private final static Logger LOGGER = LogManager.getLogger();
    protected static String hiddenWord;
    private boolean isGameActive = true;

    public ConsoleHangman() {
    }

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) {
        ConsoleHangman hangman = new ConsoleHangman();
        hangman.run();
    }

    public void run() {
        greet();
        try (Scanner scanner = new Scanner(System.in)) {
            while (isGameActive) {
                hiddenWord = Dictionary.choseRandomWord();
                askToPlay(scanner);
                if (!isGameActive) {
                    break;
                }
                GameExecutor game = new GameExecutor(hiddenWord);
                GameStatus status = game.play(scanner);
                filterResult(status);
            }
        }
    }

    private void filterResult(GameStatus status) {
        if (status == GameStatus.Surrendered) {
            LOGGER.info(ConsoleOutput.ANSWER + hiddenWord);
            isGameActive = false;
        } else if (status == GameStatus.Loser) {
            LOGGER.info(ConsoleOutput.ANSWER + hiddenWord);
        }
        LOGGER.info(status.getOutputResult());
    }

    private void greet() {
        LOGGER.info(ConsoleOutput.GREETING);
    }

    private void askToPlay(Scanner scanner) {
        LOGGER.info(ConsoleOutput.ASK_TO_PLAY);
        String userInput = scanner.next();
        isGameActive = !Objects.equals(userInput, ConsoleOutput.QUIT);
    }
}
