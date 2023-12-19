package edu.hw10.task1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RandomObjectGeneratorTest {

    @Test
    void testWithRecord() {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        PersonRecord person = generator.nextObject(PersonRecord.class);
        assertThat(person.hasJob() == false || person.hasJob() == true);
        assertThat(person.age()).isGreaterThan(0).isLessThan(101);
        assertThat(person.name()).isNotNull();
    }

    @Test
    void testGeneratorForClassWithFactoryMethod() {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        PersonClassFactory person = generator.nextObject(PersonClassFactory.class, "create");
        assertThat(person.getHasJob() == false || person.getHasJob() == true);
        assertThat(person.getAge()).isGreaterThan(0).isLessThan(101);
        assertThat(person.getName()).isNotNull();
    }
}
