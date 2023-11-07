package edu.hw4;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnimalFunctions {
    private static final String NULL_ERROR_MSG = "List can not be null";

    private AnimalFunctions() {
    }

    //Task1
    public static List<Animal> sortByHeight(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());
    }

    //Task2
    public static List<Animal> sortByWeightReturnK(List<Animal> animals, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("Number must be positive");
        }
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k).collect(Collectors.toList());
    }

    //Task3
    public static Map<Animal.Type, Long> countAnimalsByTypes(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    //Task4
    public static Animal findAnimalWithLongestName(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElse(null);
    }

    //Task5
    public static Animal.Sex getMostCommonSex(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        long female = animals.stream()
            .filter(animal -> animal.sex() == Animal.Sex.F)
            .count();
        long male = animals.stream()
            .filter(animal -> animal.sex() == Animal.Sex.M)
            .count();
        return (female > male) ? Animal.Sex.F : Animal.Sex.M;
    }

    //Task6
    public static Map<Animal.Type, Animal> getHeaviestAnimalForEachType(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
            ));
    }

    //Task7
    public static Animal getOldestAnimal(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .max(Comparator.comparingInt(Animal::age))
            .orElse(null);
    }

    //Task8
    public static Optional<Animal> getHeaviestLowerK(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException(NULL_ERROR_MSG);
        }
        if (k <= 0) {
            throw new IllegalArgumentException("K must be positive");
        }
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    //Task9
    public static Integer countAllPaws(List<Animal> animals) {
        if (animals == null) {
            return -1;
        }
        return animals.stream()
            .map(Animal::paws)
            .reduce(0, Integer::sum);
    }

    //Task10
    public static List<Animal> getListAgesNotEqualsPaws(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .filter(animal -> animal.paws() != animal.age())
            .collect(Collectors.toList());
    }

    //Task11
    public static List<Animal> getListAnimalsBiteHigherK(List<Animal> animals) {
        final int maxHeight = 100;
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .filter(animal -> (animal.bites() && animal.height() > maxHeight))
            .collect(Collectors.toList());
    }

    //Task12
    public static Long countWeightExceedsHeight(List<Animal> animals) {
        if (animals == null) {
            return -1L;
        }
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .count();
    }

    //Task13
    public static List<Animal> getAnimalsWithNamesOfTwoOrMoreWords(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .filter(animal -> animal.name().contains(" "))
            .collect(Collectors.toList());
    }

    //Task14
    public static Boolean isThereDogHigherK(List<Animal> animals, int k) {
        if (animals == null) {
            throw new NullPointerException(NULL_ERROR_MSG);
        }
        if (k <= 0) {
            throw new IllegalArgumentException("Height must be positive");
        }
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    //Task15
    public static Map<Animal.Type, Integer> countTotalWeightOfEachType(List<Animal> animals, int k, int l) {
        if (animals == null) {
            return null;
        }
        if (k <= 0 || l <= 0 || k >= l) {
            throw new IllegalArgumentException("Age range must be correct");
        }
        return animals.stream()
            .filter(animal -> k <= animal.age() && animal.age() <= l)
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.summingInt(Animal::weight)
            ));
    }

    //Task16
    public static List<Animal> sortTypeSexName(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .sorted(Comparator
                .comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    //Task17
    public static Boolean isSpidersBiteMoreOftenDogs(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerException(NULL_ERROR_MSG);
        }
        List<Animal> dogsAndSpiders = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG || animal.type() == Animal.Type.SPIDER).toList();
        long dogBites = dogsAndSpiders.stream()
            .filter(dog -> dog.type() == Animal.Type.DOG && dog.bites())
            .count();
        long spiderBites = dogsAndSpiders.stream()
            .filter(spider -> spider.type() == Animal.Type.SPIDER && spider.bites())
            .count();
        long dogs = dogsAndSpiders.stream().filter(animal -> animal.type() == Animal.Type.DOG)
            .count();
        long spiders = dogsAndSpiders.stream().filter(animal -> animal.type() == Animal.Type.SPIDER)
            .count();
        return (double) spiderBites / spiders > (double) dogBites / dogs;
    }

    //Task18
    public static Optional<Animal> findHeaviestFish(List<Animal>... animals) {
        if (animals == null) {
            throw new NullPointerException(NULL_ERROR_MSG);
        }
        return Stream.of(animals)
            .filter(Objects::nonNull)
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight));
    }

    //Task19
    public static Map<String, Set<ValidationError>> getAnimalsWithErrors(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream()
            .map(animal -> new AbstractMap.SimpleEntry<>(animal.name(), ValidationError.validate(animal)))
            .filter(entry -> !entry.getValue().isEmpty())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
            ));
    }

    //Task20
    public static Map<String, String> getAnimalsWithErrorsToStringMap(Map<String, Set<ValidationError>> errorMap) {
        if (errorMap == null) {
            return null;
        }
        return errorMap.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> entry.getValue().stream()
                    .map(ValidationError::toString)
                    .collect(Collectors.joining(" "))
            ));
    }
}
