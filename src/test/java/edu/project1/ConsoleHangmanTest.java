package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.assertNothingWrittenToSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleHangmanTest {

    @Test
    @DisplayName("Test game: input incorrect value")
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

    @Test
    @DisplayName("Test game input quit")
    void testGameWithQuit() throws Exception {
        withTextFromSystemIn("yes\n", "quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    ConsoleHangman.main(new String[] {});
                });
                assertNothingWrittenToSystemOut(() -> {
                });
            });
    }
}
