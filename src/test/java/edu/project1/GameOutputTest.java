package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.assertNothingWrittenToSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.assertj.core.api.Assertions.assertThat;

public class GameOutputTest {

    @Test
    @DisplayName("Testing game, input <quit>")
    void testWithGiveUpShouldEmptyOutputAndFinish() throws Exception {
        withTextFromSystemIn("quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    ConsoleHangman.main(new String[] {});
                });
                assertNothingWrittenToSystemOut(() -> {
                });
            });
    }

    @Test
    @DisplayName("Test game with <quit> input")
    void testGameWithQuitInputShouldQuitMsg() throws Exception {
        withTextFromSystemIn("yes\n", "quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    ConsoleHangman.main(new String[] {});
                });
                assertThat(output)
                    .contains("Don't give up");
            });
    }

    @Test
    @DisplayName("Test game with invalid input")
    void testGameWithIncorrectInputShouldOutputError() throws Exception {
        withTextFromSystemIn("yes\n", "abc\n", "quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    ConsoleHangman.main(new String[] {});
                });
                assertThat(output)
                    .contains("Something went wrong");
            });
    }

    @Test
    @DisplayName("Test game with mistakes")
    void testGameWithIncorrectInputShouldOutputLoser() throws Exception {
        withTextFromSystemIn("yes\n", "1\n", "2\n", "3\n", "4\n", "5\n", "quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    ConsoleHangman.main(new String[] {});
                });
                assertThat(output)
                    .contains("You lost");
            });
    }

    @Test
    @DisplayName("Test game with correct input")
    void testGameWithIncorrectInputShouldOutputWinner() throws Exception {
        withTextFromSystemIn("yes\n", "c\n", "a\n", "t\n", "quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    ConsoleHangman.main(new String[] {"cat"});
                });
                assertThat(output)
                    .contains("You won");
            });
    }
}
