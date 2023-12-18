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
        Annotation annotation = parameter.getAnnotation(NotNull.class);
        if (annotation != null) {
            return random.nextBoolean();
        } else {
            return switch (random.nextInt(3)) {
                case 0 -> false;
                case 1 -> true;
                default -> null;
            };

        }
    }
}
