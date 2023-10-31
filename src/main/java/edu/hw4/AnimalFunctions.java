package edu.hw4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnimalFunctions {

    private AnimalFunctions() {
    }

    //Task1
    public static List<Animal> sortByHeight(List<Animal> animals) {
        if (animals == null || animals.isEmpty()) {
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
        if (animals == null || animals.isEmpty()) {
            return null;
        }
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(Math.min(k, animals.size())).collect(Collectors.toList());
    }

    //Task3
    public static Map<Animal.Type, Integer> countAnimalsByTypes(List<Animal> animals) {
        if (animals == null || animals.isEmpty()) {
            return null;
        }
        Map<Animal.Type, Integer> map = new HashMap<>();
        animals.forEach(p -> map.put(p.type(), map.getOrDefault(p.type(), 0) + 1));
        return map;
    }

    //Task4
    public static Animal findLongestName(List<Animal> animals) {
        if (animals == null) {
            return null;
        }
        return animals.stream().
        max(Comparator.comparingInt(p -> p.name().length())).orElse(null);
    }

    //Task5
//Каких животных больше: самцов или самок -> Sex
    public static Animal.Sex getMostCommonSex(List<Animal> animals) {
        List<Animal> female = animals.stream().filter(p -> p.sex() == Animal.Sex.F).toList();
        List<Animal> male = animals.stream().filter(p -> p.sex() == Animal.Sex.M).toList();
        return (female.size() > male.size()) ? Animal.Sex.F : Animal.Sex.M;
    }



    public static void main(String[] args) {
        List<Animal> list = null;
        countAnimalsByTypes(list);
    }
}
