package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;
import java.util.Random;

public class BooleanGenerator implements Generator<Boolean> {
    private final Random random;
    private final int numberOfVariants = 3;

    public BooleanGenerator() {
        this.random = new Random();
    }

    @Override
    public Boolean generate(Parameter parameter) {
        Annotation annotation = parameter.getAnnotation(NotNull.class);
        if (annotation != null) {
            return random.nextBoolean();
        } else {
            return switch (random.nextInt(numberOfVariants)) {
                case 0 -> false;
                case 1 -> true;
                default -> null;
            };

        }
    }
}
