package edu.project1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameExecutorTest {

    @Test
    @DisplayName("Testing game, 5 mistakes, expected GameStatus = Loser")
    public void testShouldReturnLoser() {
        GameExecutor game = new GameExecutor("kitten");
        game.guess("a");
        game.guess("a");
        game.guess("a");
        game.guess("a");
        game.guess("a");
        GameStatus result = game.status;
        assertEquals(GameStatus.Loser, result);
    }

    @Test
    @DisplayName("Testing game, all symbols are guessed, expected GameStatus = Winner")
    public void testShouldReturnWinner() {

        GameExecutor game = new GameExecutor("kitten");
        game.guess("k");
        game.guess("i");
        game.guess("e");
        game.guess("n");
        game.guess("t");
        game.guess("t");
        GameStatus result = game.status;
        assertEquals(GameStatus.Winner, result);
    }

    @Test
    @DisplayName("Testing game, input <quit>, expected GameStatus = Surrendered")
    public void testWithGiveUpShouldReturnSurrendered() {
        GameExecutor game = new GameExecutor("kitten");
        game.guess("quit");
        GameStatus result = game.status;
        assertEquals(GameStatus.Surrendered, result);
    }
}
