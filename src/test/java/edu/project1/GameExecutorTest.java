package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameExecutorTest {

    @Test
    @DisplayName("Testing game, all symbols are guessed, duplicate symbols included, expected GameStatus = Winner")
    public void testShouldReturnWinner_1() {
        ConsoleHangman.hiddenWord = "kitten";
        GameExecutor game = new GameExecutor(ConsoleHangman.hiddenWord);
        game.guess('k');
        game.guess('i');
        game.guess('e');
        game.guess('n');
        game.guess('t');
        GameStatus result = game.status;
        assertEquals(GameStatus.Winner, result);
    }

    @Test
    @DisplayName("Testing game, all symbols are guessed, expected GameStatus = Winner")
    public void testShouldReturnWinner_2() {
        ConsoleHangman.hiddenWord = "house";
        GameExecutor game = new GameExecutor(ConsoleHangman.hiddenWord);
        game.guess('h');
        game.guess('i');
        game.guess('o');
        game.guess('u');
        game.guess('s');
        game.guess('e');
        GameStatus result = game.status;
        assertEquals(GameStatus.Winner, result);
    }

    @Test
    @DisplayName("Testing game, 5 mistakes, expected GameStatus = Loser")
    public void testShouldReturnLoser() {
        ConsoleHangman.hiddenWord = "kitten";
        GameExecutor game = new GameExecutor(ConsoleHangman.hiddenWord);
        game.guess('a');
        game.guess('b');
        game.guess('a');
        game.guess('c');
        game.guess('r');
        game.guess('y');
        GameStatus result = game.status;
        assertEquals(GameStatus.Loser, result);
    }

    @Test
    @DisplayName("Test game input quit")
    void testGameWithQuit() throws Exception {
        withTextFromSystemIn("yes\n", "quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    ConsoleHangman.main(new String[] {});
                });
                assertThat(output)
                    .contains(ConsoleOutput.SURRENDERED_STATUS);
            });
    }

    @Test
    @DisplayName("Test game: input incorrect value and quit")
    void testGameWithIncorrectInput() throws Exception {
        withTextFromSystemIn("yes\n", "abc\n", "quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    ConsoleHangman.main(new String[] {});
                });
                assertThat(output)
                    .contains(ConsoleOutput.ERROR_STATUS);
            });
    }
}
