package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Random;

public class BooleanGenerator implements Generator<Boolean> {
    private final Random random;

    public BooleanGenerator() {
        this.random = new Random();
    }

    @Override
    public Boolean generate(Parameter parameter) {
        Boolean generatedValue = random.nextBoolean();
        for (Annotation annotation : parameter.getAnnotations()) {
            if (annotation instanceof NotNull) {
                generatedValue = true;
            }
        }
        return generatedValue;
    }
}
