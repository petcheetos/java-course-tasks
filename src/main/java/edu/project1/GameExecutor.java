package edu.project1;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GameExecutor {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger();
    public GameStatus status = GameStatus.Default;
    private static final int MAX_ATTEMPS = 5;
    private char[] charArrHiddenWord;
    private char[] userAnswers;
    int attempts = 0;

    public GameExecutor(String hiddenWord) {
        charArrHiddenWord = hiddenWord.toCharArray();
        userAnswers = new char[charArrHiddenWord.length];
        for (int i = 0; i < charArrHiddenWord.length; i++) {
            userAnswers[i] = '*';
        }
    }

    public GameStatus play() {
        printHiddenString();
        while (status == GameStatus.Default) {
            String inputString = input();
            if (inputString.length() > 1) {
                return GameStatus.Error;
            }
            guess(inputString);
        }
        return status;
    }

    public void guess(String inputString) {
        if (Objects.equals(inputString, "quit")) {
            status = GameStatus.Surrendered;
        }
        while (attempts < MAX_ATTEMPS) {
            int index = findSymbol(inputString);
            if (index != -1) {
                LOGGER.info("Hit!");
                userAnswers[index] = inputString.charAt(0);
                charArrHiddenWord[index] = '*';
                status = (isWinner()) ? GameStatus.Winner : GameStatus.Default;
                break;
            } else {
                attempts++;
                LOGGER.info("Missed, mistake " + attempts + " out of " + MAX_ATTEMPS + ".");
                if (attempts == MAX_ATTEMPS) {
                    status = GameStatus.Loser;
                }
                break;
            }
        }
        printHiddenString();
    }

    private void printHiddenString() {
        LOGGER.info("The word: " + Arrays.toString(userAnswers));
    }

    private int findSymbol(String inputString) {
        for (int i = 0; i < charArrHiddenWord.length; i++) {
            if (charArrHiddenWord[i] == inputString.charAt(0)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isWinner() {
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] == '*') {
                return false;
            }
        }
        return true;
    }

    private String input() {
        LOGGER.info("Input a symbol: ");
        return SCANNER.next();
    }
}
