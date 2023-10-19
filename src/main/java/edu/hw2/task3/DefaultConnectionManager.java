package edu.hw2.task3;

public class DefaultConnectionManager implements ConnectionManager {
    public static int countOfActiveConnections = 0;
    public static int faultyConnectionFrequency = 2;

    public DefaultConnectionManager() {
    }

    public DefaultConnectionManager(int frequency) {
        faultyConnectionFrequency = frequency;
    }

    @Override
    public Connection getConnection() {
        countOfActiveConnections++;
        if (countOfActiveConnections % faultyConnectionFrequency == 0) {
            return new FaultyConnection();
        }
        return new StableConnection();
    }
}
