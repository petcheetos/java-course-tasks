package edu.project1;

public enum GameStatus {
    Default("You are playing"),
    Winner("You won"),
    Loser("You lost"),
    Surrendered("Don't give up"),
    Error("Something went wrong");

    private final String resultOutput;

    GameStatus(String resultOutput) {
        this.resultOutput = resultOutput;
    }

    public String getOutputResult() {
        return resultOutput;
    }

}
