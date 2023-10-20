package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;
    private final static Logger LOGGER = LogManager.getLogger();

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        ConnectionException caused = null;
        int attemps = 0;
        while (attemps < maxAttempts) {
            try (Connection newConnection = manager.getConnection()) {
                newConnection.execute(command);
                return;
            } catch (ConnectionException exception) {
                attemps++;
                caused = exception;
            }
        }
        if (caused != null) {
            throw new ConnectionException("no more connection attempts", caused);
        }
    }
}
