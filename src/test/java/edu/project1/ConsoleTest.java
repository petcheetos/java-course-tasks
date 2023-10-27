package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.assertNothingWrittenToSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;

public class ConsoleTest {

    @Test
    @DisplayName("Testing game, input <quit>")
    void testWithGiveUpShouldEmptyOutputAndFinish() throws Exception {
        withTextFromSystemIn("quit\n")
            .execute(() -> {
                String output = tapSystemOut(() -> {
                    ConsoleHangman hangman = new ConsoleHangman();
                    hangman.main(new String[] {});
                });
                assertNothingWrittenToSystemOut(() -> {
                });
            });
    }
}

