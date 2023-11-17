package edu.hw5.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest {

    @Test
    void testCheckPasswordContainsSpecialSymbol() {
        assertTrue(PasswordChecker.checkPasswordContainsSpecialSymbol("password!"));
        assertTrue(PasswordChecker.checkPasswordContainsSpecialSymbol("passw@ord"));
        assertTrue(PasswordChecker.checkPasswordContainsSpecialSymbol("&password"));
        assertFalse(PasswordChecker.checkPasswordContainsSpecialSymbol("password"));
    }
}
