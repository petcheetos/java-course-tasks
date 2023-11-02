package edu.hw4;

import java.util.Set;
import java.util.TreeSet;

public enum ValidationError {

    WRONG_NAME,
    WRONG_TYPE,
    WRONG_SEX,
    WRONG_AGE,
    WRONG_HEIGHT,
    WRONG_WEIGHT,
    OK;

    public static Set<ValidationError> validate(Animal animal) {
        Set<ValidationError> errorSet = new TreeSet<>();
        if (checkName(animal.name()) == ValidationError.WRONG_NAME) {
            errorSet.add(ValidationError.WRONG_NAME);
        }
        if (checkType(animal.type()) == ValidationError.WRONG_TYPE) {
            errorSet.add(ValidationError.WRONG_TYPE);
        }
        if (checkSex(animal.sex()) == ValidationError.WRONG_SEX) {
            errorSet.add(ValidationError.WRONG_SEX);
        }
        if (checkAge(animal.age()) == ValidationError.WRONG_AGE) {
            errorSet.add(ValidationError.WRONG_AGE);
        }
        if (checkHeight(animal.height()) == ValidationError.WRONG_HEIGHT) {
            errorSet.add(ValidationError.WRONG_HEIGHT);
        }
        if (checkWeight(animal.weight()) == ValidationError.WRONG_WEIGHT) {
            errorSet.add(ValidationError.WRONG_WEIGHT);
        }
        return errorSet;
    }

    private static ValidationError checkName(String name) {
        if (name == null || name.isEmpty()) {
            return ValidationError.WRONG_NAME;
        }
        return ValidationError.OK;
    }

    private static ValidationError checkType(Animal.Type type) {
        if (type == null) {
            return ValidationError.WRONG_TYPE;
        }
        return ValidationError.OK;
    }

    private static ValidationError checkSex(Animal.Sex sex) {
        if (sex == null) {
            return ValidationError.WRONG_SEX;
        }
        return ValidationError.OK;
    }

    private static ValidationError checkAge(int age) {
        if (age <= 0) {
            return ValidationError.WRONG_AGE;
        }
        return ValidationError.OK;
    }

    private static ValidationError checkHeight(int height) {
        if (height <= 0) {
            return ValidationError.WRONG_HEIGHT;
        }
        return ValidationError.OK;
    }

    private static ValidationError checkWeight(int weight) {
        if (weight <= 0) {
            return ValidationError.WRONG_WEIGHT;
        }
        return ValidationError.OK;
    }
}
