package edu.hw5.task8;

import java.util.regex.Pattern;

public class ExtraRegularExpression {

    private ExtraRegularExpression() {
    }

    public static boolean isOddLength(String string) {
        Pattern pattern = Pattern.compile("^[01]([01][01])*$");
        return pattern.matcher(string).matches();
    }

    public static boolean isNot11Or111(String string) {
        Pattern pattern = Pattern.compile("^(?!11$|111$)[01]*$");
        return pattern.matcher(string).matches();
    }

    public static boolean isNotConsecutiveOnes(String string) {
        Pattern pattern = Pattern.compile("^(?!.*11)[01]*$");
        return pattern.matcher(string).matches();
    }

    public static boolean isEachOddSymbolOne(String string) {
        Pattern pattern = Pattern.compile("^((10)+1?)|(1)*$");
        return pattern.matcher(string).matches();
    }
}
