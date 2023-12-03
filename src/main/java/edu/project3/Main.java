package edu.project3;

import edu.project3.log.LogAnalysisExecutor;

public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        if (args == null) {
            throw new NullPointerException();
        }
        ConsoleHandler handler = new ConsoleHandler(args);
        ConsoleHandler.ConsoleCommand command = handler.getCommand();
        if (command != null) {
            LogAnalysisExecutor.run(command);
        }
    }
}
