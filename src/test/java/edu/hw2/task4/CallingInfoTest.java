package edu.hw2.task4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallingInfoTest {

    @Test
    void testCallingInfoTest() {
        CallingInfo info = new CallingInfo("edu.hw2.task4.CallingInfo", "callingInfo");
        assertEquals(info, firstCall());
        assertEquals(info, secondCall());
        assertEquals(info, thirdCall());
    }

    private static CallingInfo firstCall() {
        return CallingInfo.callingInfo();
    }

    private static CallingInfo secondCall() {
        return firstCall();
    }

    private static CallingInfo thirdCall() {
        return secondCall();
    }
}
