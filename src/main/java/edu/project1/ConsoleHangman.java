package edu.project1;

import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final static Logger LOGGER = LogManager.getLogger();
    private static boolean isGameActive = false;

    public void run() {
        greet();
        askToPlay();
        while (isGameActive) {
            Session session = new Session();
            session.run();
            printResult(session);
            askToPlay();
        }
    }

    private void printResult(Session session) {
        LOGGER.info(session.status.getOutputResult());
    }

    private void greet() {
        LOGGER.info("Welcome to Hangman game!");
    }

    private void askToPlay() {
        LOGGER.info("Do you want to play? Write \"quit\" to leave the game");
        String userInput = SCANNER.next();
        isGameActive = !Objects.equals(userInput, "quit");
    }
}
