package edu.project3;

import edu.project3.log.LogAnalysisExecutor;

public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        ConsoleHandler handler = new ConsoleHandler(args);
        ConsoleHandler.ConsoleCommand command = handler.getCommand();
        LogAnalysisExecutor.run(command);
    }
}


