package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalFunctions.sortByWeightReturnK(List.of(), 0)
        );
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
        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalFunctions.isThereDogHigherK(List.of(), 0)
        );

        assertThrows(
            NullPointerException.class,
            () -> AnimalFunctions.isThereDogHigherK(null, 10)
        );
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

    @Test
    @DisplayName("Testing task11")
    void testGetListAnimalsBiteHigherK() {
        assertEquals(
            List.of(
                new Animal("BigBadDog", Animal.Type.DOG, Animal.Sex.F, 4, 101, 17, true),
                new Animal("AngryPeacock", Animal.Type.BIRD, Animal.Sex.M, 2, 103, 10, true)
            ),
            AnimalFunctions.getListAnimalsBiteHigherK(List.of(
                new Animal("BigBadDog", Animal.Type.DOG, Animal.Sex.F, 4, 101, 17, true),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Peacock", Animal.Type.BIRD, Animal.Sex.M, 2, 103, 10, false),
                new Animal("AngryPeacock", Animal.Type.BIRD, Animal.Sex.M, 2, 103, 10, true),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 4, 32, 3, true)
            ))
        );

        assertNull(AnimalFunctions.getListAnimalsBiteHigherK(null));
    }

    @Test
    @DisplayName("Testing task12")
    void testCountWeightExceedsHeight() {
        assertEquals(2, AnimalFunctions.countWeightExceedsHeight(List.of(
            new Animal("heavy", Animal.Type.DOG, Animal.Sex.M, 2, 30, 45, true),
            new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
            new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 6, false),
            new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 1, false)
        )));

        assertEquals(-1L, AnimalFunctions.countWeightExceedsHeight(null));
    }

    @Test
    @DisplayName("Testing task13")
    void testGetAnimalsWithNamesOfTwoOrMoreWords() {
        assertEquals(
            List.of(
                new Animal("Mister Bob", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
                new Animal("Pretty kitten", Animal.Type.CAT, Animal.Sex.F, 1, 15, 2, false)
            ),
            AnimalFunctions.getAnimalsWithNamesOfTwoOrMoreWords(List.of(
                new Animal("Mister Bob", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Pretty kitten", Animal.Type.CAT, Animal.Sex.F, 1, 15, 2, false),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 6, false),
                new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 1, false)
            ))
        );

        assertNull(AnimalFunctions.getAnimalsWithNamesOfTwoOrMoreWords(null));
    }

    @Test
    @DisplayName("Testing task14")
    void testIsThereDogHigherK() {
        assertEquals(true, AnimalFunctions.isThereDogHigherK(
            List.of(
                new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 6, false),
                new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 1, false)
            ), 40
        ));
        assertEquals(false, AnimalFunctions.isThereDogHigherK(
            List.of(
                new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 5, 6, false),
                new Animal("Parrot", Animal.Type.BIRD, Animal.Sex.M, 3, 20, 1, false)
            ), 100
        ));
    }

    @Test
    void testIsThereDogHigherKWithIncorrectArgs() {
        assertThrows(
            NullPointerException.class,
            () -> AnimalFunctions.isThereDogHigherK(null, 10)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalFunctions.isThereDogHigherK(List.of(), 0)
        );
    }

    @Test
    @DisplayName("Testing task15")
    void testCountTotalWeightOfEachType() {
        Map<Animal.Type, Integer> map = new HashMap<>();
        map.put(Animal.Type.DOG, 10);
        map.put(Animal.Type.CAT, 2);
        map.put(Animal.Type.FISH, 1);

        assertEquals(map, AnimalFunctions.countTotalWeightOfEachType(
            List.of(
                new Animal("Dog", Animal.Type.DOG, Animal.Sex.F, 2, 43, 5, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 7, 54, 10, true),
                new Animal("Bob", Animal.Type.DOG, Animal.Sex.M, 3, 54, 5, true),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 3, 30, 2, true),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false)
            ), 1, 4)
        );
    }

    @Test
    void testCountTotalWeightOfEachTypeWithIncorrectArgs() {
        assertThrows(
            NullPointerException.class,
            () -> AnimalFunctions.countTotalWeightOfEachType(List.of(), 10, -1)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalFunctions.countTotalWeightOfEachType(List.of(), -10, 10)
        );
        assertThrows(
            IllegalArgumentException.class,
            () -> AnimalFunctions.countTotalWeightOfEachType(List.of(), 10, 5)
        );
        assertNull(AnimalFunctions.countTotalWeightOfEachType(null, 10, 16));
    }

    @Test
    @DisplayName("Testing task16")
    void testSortTypeSexName() {
        assertEquals(
            List.of(
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false),
                new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 32, 3, true),
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false)
            ),
            AnimalFunctions.sortTypeSexName(List.of(
                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, true),
                new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 32, 3, true),
                new Animal("Bob", Animal.Type.CAT, Animal.Sex.M, 5, 38, 7, false),
                new Animal("Jack", Animal.Type.DOG, Animal.Sex.M, 3, 54, 10, true),
                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 1, 10, 1, false)
            ))
        );

        assertNull(AnimalFunctions.sortTypeSexName(null));
    }

    @Test
    @DisplayName("Testing task17")
    void testIsSpidersBiteMoreOftenDogs() {
        assertEquals(false, AnimalFunctions.isSpidersBiteMoreOftenDogs(List.of(
            new Animal("Mary", Animal.Type.DOG, Animal.Sex.F, 1, 32, 3, true),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 1, 32, 3, true),
            new Animal("Bob", Animal.Type.DOG, Animal.Sex.M, 5, 38, 7, false),
            new Animal("Jack", Animal.Type.SPIDER, Animal.Sex.M, 3, 54, 10, true),
            new Animal("Nemo", Animal.Type.SPIDER, Animal.Sex.M, 1, 10, 1, false)
        )));
        assertEquals(true, AnimalFunctions.isSpidersBiteMoreOftenDogs(List.of(
            new Animal("Mary", Animal.Type.DOG, Animal.Sex.F, 1, 32, 3, false),
            new Animal("Cat", Animal.Type.DOG, Animal.Sex.M, 1, 32, 3, false),
            new Animal("Bob", Animal.Type.DOG, Animal.Sex.M, 5, 38, 7, false),
            new Animal("Jack", Animal.Type.SPIDER, Animal.Sex.M, 3, 54, 10, true),
            new Animal("Nemo", Animal.Type.SPIDER, Animal.Sex.M, 1, 10, 1, false)
        )));
        assertEquals(false, AnimalFunctions.isSpidersBiteMoreOftenDogs(List.of(
            new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, false),
            new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 32, 3, false)
        )));

        assertThrows(
            NullPointerException.class,
            () -> AnimalFunctions.isSpidersBiteMoreOftenDogs(null));
    }

//    @Test
//    @DisplayName("Testing task18")
//    void testFindHeaviestFish() {
//        assertEquals(
//            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 20, 5, false),
//            AnimalFunctions.findHeaviestFish(List.of(
//                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, false),
//                new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 32, 3, false),
//                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 20, 3, false)
//            ), List.of(
//                new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 2, 20, 5, false),
//                new Animal("Mary", Animal.Type.CAT, Animal.Sex.F, 1, 32, 3, false),
//                new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 1, 32, 3, false)
//            ))
//        );
//    }
}
