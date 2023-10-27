package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.assertj.core.api.Assertions.assertThat;

public class GameOutputTest {

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
}
