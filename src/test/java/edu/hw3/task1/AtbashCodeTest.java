package edu.hw3.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AtbashCodeTest {
    @Test
    void testEmptyStringShouldReturnException() {
        assertThrows(
            NullPointerException.class,
            () -> AtbashCode.atbash("")
        );
    }

    @CsvSource({
        "Hello world!, Svool dliow!",
        "pineapple?, krmvzkkov?",
        "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler, Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi",
        "Kitten, Prggvm"})
    @ParameterizedTest
    void testWithCsvSource(String testStr, String expectedResult) {
        assertEquals(AtbashCode.atbash(testStr), expectedResult);
    }
}
