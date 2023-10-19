package edu.hw2.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        LOGGER.info("Execute faulty connection");
        if ((int) (Math.random() * 2) == 1) {
            throw new ConnectionException("Connection error");
        }
    }

    @Override
    public void close() {
        LOGGER.info("Faulty connection is closed");
    }
}
