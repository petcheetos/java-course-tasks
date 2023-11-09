package edu.project1;

public enum GameStatus {
    Default(ConsoleOutput.DEFAULT_STATUS),
    Winner(ConsoleOutput.WINNER_STATUS),
    Loser(ConsoleOutput.LOSER_STATUS),
    Surrendered(ConsoleOutput.SURRENDERED_STATUS),
    Error(ConsoleOutput.ERROR_STATUS);

    private final String resultOutput;

    GameStatus(String resultOutput) {
        this.resultOutput = resultOutput;
    }

    public String getOutputResult() {
        return resultOutput;
    }

}
