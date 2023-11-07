package edu.project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class GameExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    public GameStatus status = GameStatus.Default;
    private static final int MAX_ATTEMPTS = 5;
    private final char[] maskedWord;
    private final char star = '*';
    private final Set<Character> userInput = new HashSet<>();
    private int attempts = 0;

    public GameExecutor(String hiddenWord) {
        maskedWord = new char[hiddenWord.length()];
        for (int i = 0; i < hiddenWord.length(); i++) {
            maskedWord[i] = star;
        }
    }

    public GameStatus play(Scanner scanner) {
        printHiddenString();
        while (status == GameStatus.Default) {
            String inputString = input(scanner);
            if (Objects.equals(inputString, ConsoleOutput.QUIT)) {
                return GameStatus.Surrendered;
            }
            if (inputString.length() > 1) {
                LOGGER.info(ConsoleOutput.ERROR_STATUS);
                continue;
            }
            status = guess(inputString.charAt(0));
        }
        return status;
    }

    protected GameStatus guess(char symbol) {
        if (userInput.contains(symbol)) {
            LOGGER.info(ConsoleOutput.REPEATED_SYMBOL);
            return status;
        }
        userInput.add(symbol);
        if (attempts < MAX_ATTEMPTS) {
            ArrayList<Integer> foundSymbolIndexes = findSymbol(symbol);
            if (!foundSymbolIndexes.isEmpty()) {
                LOGGER.info(ConsoleOutput.HIT);
                for (var index : foundSymbolIndexes) {
                    maskedWord[index] = symbol;
                }
                status = (isWinner()) ? GameStatus.Winner : GameStatus.Default;
            } else {
                attempts++;
                LOGGER.info(ConsoleOutput.MISTAKE + attempts + "/" + MAX_ATTEMPTS);
                if (attempts == MAX_ATTEMPTS) {
                    status = GameStatus.Loser;
                }
            }
        }
        printHiddenString();
        return status;
    }

    private void printHiddenString() {
        LOGGER.info(ConsoleOutput.WORD_IN_BRACKETS + Arrays.toString(maskedWord));
    }

    private ArrayList<Integer> findSymbol(char symbol) {
        ArrayList<Integer> symbolsIndexes = new ArrayList<>();
        for (int i = 0; i < ConsoleHangman.hiddenWord.length(); i++) {
            if (ConsoleHangman.hiddenWord.charAt(i) == symbol) {
                symbolsIndexes.add(i);
            }
        }
        return symbolsIndexes;
    }

    private boolean isWinner() {
        for (char c : maskedWord) {
            if (c == star) {
                return false;
            }
        }
        return true;
    }

    private String input(Scanner scanner) {
        LOGGER.info(ConsoleOutput.ASK_TO_INPUT);
        return scanner.next();
    }
}
