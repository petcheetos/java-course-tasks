package edu.hw8.task1;

import java.io.IOException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int NUMBER_OF_THREADS = 3;

    public static void main(String[] args) {
        boolean serverIsWorking = false;
        boolean programIsWorking = true;
        KeywordQuoteServer server = null;
        KeywordQuoteClient client = null;
        Thread serverThread = null;
        greet();

        try (Scanner scanner = new Scanner(System.in)) {
            while (programIsWorking) {
                String command = scanner.next();
                switch (command) {
                    case "--start":
                        if (!serverIsWorking) {
                            server = new KeywordQuoteServer("localhost", 8080, NUMBER_OF_THREADS);
                            client = new KeywordQuoteClient("localhost", 8080);
                            KeywordQuoteServer finalServer = server;
                            serverThread = new Thread(finalServer::start);

                            serverThread.start();
                            serverIsWorking = true;
                        }
                        break;

                    case "--send":
                        if (serverIsWorking) {
                            String msg = scanner.next();
                            try {
                                client.sendMessage(msg);
                            } catch (IOException e) {
                                LOGGER.error("Failed to send message", e);
                            }
                        }
                        break;

                    case "--stop":
                        if (serverIsWorking) {
                            serverThread.interrupt();
                            server.stop();
                        }
                        serverIsWorking = false;
                        programIsWorking = false;
                        break;

                    default:
                        if (serverIsWorking) {
                            serverThread.interrupt();
                        }
                        programIsWorking = false;
                }
            }
        }
    }

    public static void greet() {
        System.out.println("type --start to start server\ntype --send <msg> to send message");
        System.out.println("type --stop to stop server");
    }
}
