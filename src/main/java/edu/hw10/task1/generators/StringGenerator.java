package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Random;

public class StringGenerator implements Generator<String> {
    private final Random random;
    private final int maxStringLength = 10;
    private final int maxLetterNumber = 26;

    public StringGenerator() {
        this.random = new Random();
    }

    @Override
    public String generate(Parameter parameter) {
        String generatedValue = generateRandomString();

        for (Annotation annotation : parameter.getAnnotations()) { //аннотации не берет
            if (annotation instanceof NotNull) {
                generatedValue = generateNotNullValue();
            } else if (annotation instanceof Min || annotation instanceof Max) {
                generatedValue = generateLengthBoundedValue(parameter);
            }
        }

        return generatedValue;
    }

    private String generateNotNullValue() {
        return generateRandomString();
    }

    private String generateLengthBoundedValue(Parameter parameter) {
        int minLength = parameter.isAnnotationPresent(Min.class) ? parameter.getAnnotation(Min.class).value() : 0;
        int maxLength =
            parameter.isAnnotationPresent(Max.class) ? parameter.getAnnotation(Max.class).value() : Integer.MAX_VALUE;

        if (minLength > maxLength) {
            throw new IllegalArgumentException("Min length cannot be greater than Max length");
        }
        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        return generateRandomString(length);
    }

    private String generateRandomString() {
        return generateRandomString(random.nextInt(maxStringLength));
    }

    private String generateRandomString(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append((char) (random.nextInt(maxLetterNumber) + 'a'));
        }
        return result.toString();
    }
}
