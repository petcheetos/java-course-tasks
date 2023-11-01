package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        assertNull(AnimalFunctions.sortByHeight(null));
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
            }, "Number must be positive");

        assertNull(AnimalFunctions.sortByWeightReturnK(null, 10));
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

        assertNull(AnimalFunctions.countAnimalsByTypes(null));
    }

    @Test
    @DisplayName("Testing task4")
    void testFindAnimalWithLongestName() {
        Animal animal = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true);
        assertEquals(animal, AnimalFunctions.findAnimalWithLongestName(
            List.of(
                new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                animal,
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false)
            )
        ));

        assertNull(AnimalFunctions.findAnimalWithLongestName(null));
    }

    @Test
    @DisplayName("Testing task5")
    void testGetMostCommonSex() {
        assertEquals(Animal.Sex.M, AnimalFunctions.getMostCommonSex(List.of(
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true)
            ))
        );
        assertEquals(Animal.Sex.F, AnimalFunctions.getMostCommonSex(List.of(
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                new Animal("Jane", Animal.Type.CAT, Animal.Sex.F, 5, 38, 7, false),
                new Animal("Doo", Animal.Type.DOG, Animal.Sex.F, 3, 54, 10, true)
            ))
        );

        assertNull(AnimalFunctions.getMostCommonSex(null));
    }

    @Test
    @DisplayName("Testing task6")
    void testGetHeaviestAnimalForEachType() {
        Map<Animal.Type, Animal> map = new HashMap<>();
        map.put(Animal.Type.DOG, new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true));
        map.put(Animal.Type.CAT, new Animal("Murchik", Animal.Type.CAT, Animal.Sex.M, 7, 40, 9, true));
        map.put(Animal.Type.FISH, new Animal("Bob", Animal.Type.FISH, Animal.Sex.M, 5, 38, 7, false));
        map.put(Animal.Type.BIRD, new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 1, false));

        assertEquals(
            map,
            AnimalFunctions.getHeaviestAnimalForEachType(
                List.of(
                    new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
                    new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                    new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                    new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 1, false),
                    new Animal("Murchik", Animal.Type.CAT, Animal.Sex.M, 7, 40, 9, true),
                    new Animal("Bob", Animal.Type.FISH, Animal.Sex.M, 5, 38, 7, false)
                )
            )
        );

        assertNull(AnimalFunctions.getHeaviestAnimalForEachType(null));
    }

    @Test
    @DisplayName("Testing task7")
    void testGetOldestAnimal() {
        Animal oldest = new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 12, 54, 12, true);
        assertEquals(oldest, AnimalFunctions.getOldestAnimal(List.of(
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 12, 54, 12, true),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 1, false),
                new Animal("Murchik", Animal.Type.CAT, Animal.Sex.M, 7, 40, 9, true)
            )
        ));

        assertNull(AnimalFunctions.getOldestAnimal(null));
    }

    @Test
    @DisplayName("Testing task8")
    void testGetHeaviestLowerK() {
        Optional<Animal> animal = Optional.of(new Animal("Bob", Animal.Type.DOG, Animal.Sex.M, 5, 53, 13, true));
        assertEquals(animal, AnimalFunctions.getHeaviestLowerK(
            List.of(
                new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 6, 56, 15, true),
                new Animal("Bob", Animal.Type.DOG, Animal.Sex.M, 5, 53, 13, true),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 65, 3, true),
                new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 1, false)
            ), 55
        ));

        assertEquals(Optional.empty(), AnimalFunctions.getHeaviestLowerK(List.of(
            new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 65, 3, true),
            new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 1, false)
        ), 10));
    }

    @Test
    void testGetHeaviestLowerKWithIncorrectArgs() {
        Assertions
            .assertThrows(IllegalArgumentException.class, () -> {
                AnimalFunctions.getHeaviestLowerK(List.of(
                    new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false)), 0);
            }, "K must be positive");

        assertNull(AnimalFunctions.getHeaviestLowerK(null, 10));
    }

    @Test
    @DisplayName("Testing task9")
    void testCountAllPaws() {
        assertEquals(18, AnimalFunctions.countAllPaws(List.of(
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
            new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false),
            new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 1, false),
            new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
            new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false)
        )));

        assertEquals(-1, AnimalFunctions.countAllPaws(null));
    }

    @Test
    @DisplayName("Testing task10")
    void testGetListAgesNotEqualsPaws() {
        assertEquals(
            List.of(
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false),
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false)
            ),
            AnimalFunctions.getListAgesNotEqualsPaws(List.of(
                new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 4, 43, 5, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false),
                new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 2, 20, 1, false),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 4, 32, 3, true),
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false)
            ))
        );

        assertNull(AnimalFunctions.getListAgesNotEqualsPaws(null));
    }
}
