package edu.hw4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

//    String name,
//    Type type,
//    Sex sex,
//    int age,
//    int height,
//    int weight,
//    boolean bites
public class AnimalFunctionsTest {

    @Test
    @DisplayName("Testing task1")
    void testSortByHeight() {
        assertEquals(
            List.of(
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true)
            ),
            AnimalFunctions.sortByHeight(List.of(
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false)
            ))
        );
    }

    @Test
    void testSortByHeightWithNull() {
        assertNull(AnimalFunctions.sortByHeight(null));
        assertNull(AnimalFunctions.sortByHeight(new ArrayList<>(){}));
    }

    @Test
    @DisplayName("Testing task2")
    void testSortByWeightReturnK() {
        assertEquals(
            List.of(
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false)
            ),
            AnimalFunctions.sortByWeightReturnK(List.of(
                new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false)
            ), 2)
        );
    }

    @Test
    void testSortByWeightReturnKWithInvalidArgs() {
        Assertions
            .assertThrows(IllegalArgumentException.class, () -> {
                AnimalFunctions.sortByWeightReturnK(List.of(
                    new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false)), 0);
            }, "Number must be integer");

        assertNull(AnimalFunctions.sortByWeightReturnK(null, 10));
        assertNull(AnimalFunctions.sortByWeightReturnK(new ArrayList<>(){}, 10));
    }

    @Test
    @DisplayName("Testing task3")
    void testCountAnimalsByTypes() {
        Map<Animal.Type, Integer> map = new HashMap<>();
        map.put(Animal.Type.DOG, 2);
        map.put(Animal.Type.CAT, 2);
        map.put(Animal.Type.FISH, 1);
        assertEquals(map, AnimalFunctions.countAnimalsByTypes(
            List.of(
                new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false)
            )
        ));
    }

    @Test
    void testCountAnimalsByTypesWithNull() {
        assertNull(AnimalFunctions.countAnimalsByTypes(null));
        assertNull(AnimalFunctions.countAnimalsByTypes(new ArrayList<>()));
    }



}
