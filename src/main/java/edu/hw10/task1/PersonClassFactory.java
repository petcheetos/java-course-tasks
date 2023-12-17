package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public class PersonClassFactory {
    private final String name;
    private final int age;
    private final boolean hasJob;

    private PersonClassFactory(String name, int age, boolean hasJob) {
        this.name = name;
        this.age = age;
        this.hasJob = hasJob;
    }

    public static PersonClassFactory create(@NotNull String name, @Min(1) @Max(100) int age, boolean hasJob) {
        return new PersonClassFactory(name, age, hasJob);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getHasJob() {
        return hasJob;
    }
}
