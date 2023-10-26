package edu.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GameExecutor {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger();
    public GameStatus status = GameStatus.Default;
    private static final int MAX_ATTEMPTS = 5;
    private char[] charArrHiddenWord;
    private char[] userAnswers;
    private final char star = '*';
    private ArrayList<Character> mistakes = new ArrayList<>();
    int attempts = 0;

    public GameExecutor(String hiddenWord) {
        charArrHiddenWord = hiddenWord.toCharArray();
        userAnswers = new char[charArrHiddenWord.length];
        for (int i = 0; i < charArrHiddenWord.length; i++) {
            userAnswers[i] = star;
        }
    }

    public GameStatus play() {
        printHiddenString();
        while (status == GameStatus.Default) {
            String inputString = input();
            if (Objects.equals(inputString, ConsoleHangman.hiddenWord)) {
                return GameStatus.Surrendered;
            }
            if (inputString.length() > 1) {
                return GameStatus.Error;
            }
            status = guess(inputString.charAt(0));
        }
        return status;
    }

    private GameStatus guess(char symbol) {
        while (attempts < MAX_ATTEMPTS) {
            ArrayList<Integer> foundSymbolIndexes = findSymbol(symbol);
            if (!foundSymbolIndexes.isEmpty()) {
                LOGGER.info(ConsoleOutput.HIT);
                for (var index : foundSymbolIndexes) {
                    userAnswers[index] = symbol;
                    charArrHiddenWord[index] = star;
                }
                status = (isWinner()) ? GameStatus.Winner : GameStatus.Default;
                break;
            } else {
                if (!mistakes.contains(symbol)) {
                    mistakes.add(symbol);
                    attempts++;
                }
                LOGGER.info(ConsoleOutput.MISTAKE + attempts + "/" + MAX_ATTEMPTS);
                if (attempts == MAX_ATTEMPTS) {
                    status = GameStatus.Loser;
                }
                break;
            }
        }
        printHiddenString();
        return status;
    }

    private void printHiddenString() {
        LOGGER.info(ConsoleOutput.WORD_IN_BRACKETS + Arrays.toString(userAnswers));
    }

    private ArrayList<Integer> findSymbol(char symbol) {
        ArrayList<Integer> symbolsIndexes = new ArrayList<>();
        for (int i = 0; i < charArrHiddenWord.length; i++) {
            if (charArrHiddenWord[i] == symbol) {
                symbolsIndexes.add(i);
            }
        }
        return symbolsIndexes;
    }

    private boolean isWinner() {
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] == star) {
                return false;
            }
        }
        return true;
    }

    private String input() {
        LOGGER.info(ConsoleOutput.ASK_TO_INPUT);
        return SCANNER.next();
    }
}
