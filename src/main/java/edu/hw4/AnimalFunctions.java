package edu.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static java.util.Collections.max;

public class AnimalFunctions {

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
            .limit(Math.min(k, animals.size())).collect(Collectors.toList());
    }

    //Task3
    public static Map<Animal.Type, Integer> countAnimalsByTypes(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        Map<Animal.Type, Integer> map = new HashMap<>();
        animals.forEach(animal -> map.put(animal.type(), map.getOrDefault(animal.type(), 0) + 1));
        return map;
    }

    //Task4
    public static Animal findAnimalWithLongestName(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream().
        max(Comparator.comparingInt(animal -> animal.name().length())).orElse(null);
    }

    //Task5
    public static Animal.Sex getMostCommonSex(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        List<Animal> female = animals.stream().filter(p -> p.sex() == Animal.Sex.F).toList();
        List<Animal> male = animals.stream().filter(p -> p.sex() == Animal.Sex.M).toList();
        return (female.size() > male.size()) ? Animal.Sex.F : Animal.Sex.M;
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
            return Optional.empty();
        }
        if (k <= 0) {
            throw new IllegalArgumentException("K must be positive");
        }
        return (animals.stream().filter(animal -> animal.height() < k)).max(Comparator.comparingInt(Animal::weight));
    }

    //Task9
    public static Integer countAllPaws(List<Animal> animals) {
        if (animals == null) {
            return -1;
        }
        return animals.stream().map(Animal::paws).reduce(0, Integer::sum);
    }

    //Task10
    public static List<Animal> getListAgesNotEqualsPaws(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream().filter(animal -> animal.paws() != animal.age()).toList();
    }

    //Task11

    //Task12

    //Task13

    //Task14

    //Task15

    //Task16

    //Task17

    //Task18

    //Task19

    //Task20

}
