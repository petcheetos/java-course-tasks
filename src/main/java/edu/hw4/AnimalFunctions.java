package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

        Map<Animal.Type, Long> map;
        map = animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
        return map;
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
        Map<Animal.Type, Animal> map = new HashMap<>();
        animals.forEach(animal -> {
                if (!map.containsKey(animal.type()) || animal.weight() >= map.get(animal.type()).weight()) {
                    map.put(animal.type(), animal);
                }
            }
        );
        return map;
    }

    //Task7
    public static Animal getOldestAnimal(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        final Animal[] oldest = {animals.getFirst()};
        animals.forEach(animal -> {
            if (animal.age() > oldest[0].age()) {
                oldest[0] = animal;
            }
        });
        return oldest[0];
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
        Map<Animal.Type, Integer> map = new HashMap<>();
        animals.forEach(animal -> {
                if (k <= animal.age() && animal.age() <= l) {
                    map.put(animal.type(), map.getOrDefault(animal.type(), 0) + animal.weight());
                }
            }
        );
        return map;
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
        int value = animals.stream()
            .mapToInt(animal -> {
                if (animal.type() == Animal.Type.DOG && animal.bites()) {
                    return -1;
                } else if (animal.type() == Animal.Type.SPIDER && animal.bites()) {
                    return 1;
                } else {
                    return 0;
                }
            }).sum();
        return value > 0;
    }

    //Task18
    public static Optional<Animal> findHeaviestFish(List<Animal>... animals) {
        List<Animal> heavyFish = new ArrayList<>();
        for (List<Animal> list : animals) {
            if (list == null) {
                throw new NullPointerException(NULL_ERROR_MSG);
            }
            heavyFish.add(list.stream()
                .filter(animal -> animal.type() == Animal.Type.FISH)
                .max(Comparator.comparingInt(Animal::weight))
                .orElse(null));
        }
        return heavyFish.stream()
            .max(Comparator.comparingInt(Animal::weight));
    }

    //Task19
    public static Map<String, Set<ValidationError>> getAnimalsWithErrors(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        Map<String, Set<ValidationError>> map = new HashMap<>();
        animals
            .forEach(animal -> {
                Set<ValidationError> currSet = ValidationError.validate(animal);
                if (!currSet.isEmpty()) {
                    map.put(animal.name(), currSet);
                }
            });
        return map;
    }

    //Task20
    public static Map<String, String> getAnimalsWithErrorsToStringMap(Map<String, Set<ValidationError>> errorMap) {
        if (errorMap == null) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        errorMap.forEach(
            (String name, Set<ValidationError> set) -> {
                StringBuilder stringBuilder = new StringBuilder();
                set.forEach(error -> stringBuilder.append(error.toString()).append(" "));
                map.put(name, stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.length());
            }
        );
        return map;
    }
}
