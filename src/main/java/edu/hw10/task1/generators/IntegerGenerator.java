package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Random;

public class IntegerGenerator implements Generator<Integer> {
    private final Random random;

    public IntegerGenerator() {
        this.random = new Random();
    }

    @Override
    public Integer generate(Parameter parameter) {
        int generatedValue = random.nextInt();

        for (Annotation annotation : parameter.getAnnotations()) {
            if (annotation instanceof NotNull) {
                generatedValue = generateNotNullValue();
            } else if (annotation instanceof Min || annotation instanceof Max) {
                generatedValue = generateBoundedValue(parameter);
            }
        }
        return generatedValue;
    }

    private Integer generateNotNullValue() {
        return random.nextInt();
    }

    private Integer generateBoundedValue(Parameter parameter) {
        int minValue =
            parameter.isAnnotationPresent(Min.class) ? parameter.getAnnotation(Min.class).value()
                : Integer.MIN_VALUE;
        int maxValue =
            parameter.isAnnotationPresent(Max.class) ? parameter.getAnnotation(Max.class).value()
                : Integer.MAX_VALUE;

        if (minValue > maxValue) {
            throw new IllegalArgumentException("Min value cannot be greater than Max value");
        }
        return random.nextInt(maxValue - minValue + 1) + minValue;
    }
}
