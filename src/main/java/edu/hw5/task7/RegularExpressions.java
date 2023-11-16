package edu.hw5.task7;

import java.util.regex.Pattern;

public class RegularExpressions {

    private RegularExpressions() {
    }

    public static boolean isContainsNotLess3SymbolsAndThirdIsZero(String string) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*$");
        return pattern.matcher(string).matches();
    }

    public static boolean isStartEndWithSameSymbol(String string) {
        Pattern pattern = Pattern.compile("(^0[01]*0$)|(^1[01]*1$)");
        return pattern.matcher(string).matches();
    }

    public static boolean isLengthBetweenOneAndThree(String string) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        return pattern.matcher(string).matches();
    }
}
