package edu.hw7.task3;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonDatabaseTest {

    @Test
    void testSyncDatabase() {
        PersonDatabase database = new SyncDatabase();
        Person person1 = new Person(1, "First", "Address 1", "Phone 1");
        Person person2 = new Person(2, "Second", "Address 2", "Phone 2");
        Person person3 = new Person(3, "Third", "Address 3", "Phone 3");

        database.add(person1);
        database.add(person2);
        database.add(person3);

        assertEquals(person3, database.findByPhone("Phone 3").get(0));
        assertEquals(person1, database.findByName("First").get(0));
        assertEquals(person2, database.findByAddress("Address 2").get(0));

        database.delete(person3.id());

        assertTrue(database.findByAddress("Address 3").isEmpty());
        assertTrue(database.findByPhone("Phone 3").isEmpty());
        assertTrue(database.findByName("Third").isEmpty());
    }

    @Test
    void testSyncDataBaseThreads() {
        PersonDatabase database = new SyncDatabase();

        Person person1 = new Person(1, "First", "Address 1", "Phone 1");
        Person person2 = new Person(2, "Second", "Address 2", "Phone 2");
        Person person3 = new Person(3, "Third", "Address 3", "Phone 3");
        Person person4 = new Person(4, "yaFirst", "Address 4", "Phone 4");
        Person person5 = new Person(5, "yaSecond", "Address 5", "Phone 5");
        Person person6 = new Person(6, "yaThird", "Address 6", "Phone 6");

        List<Person> people = List.of(
            person1, person2, person3, person4, person5, person6
        );

        List<Thread> addThreads = new ArrayList<>();
        people.forEach(person -> {
                addThreads.add(new Thread(() -> database.add(person)));
            }
        );
        addThreads.forEach(Thread::start);

        addThreads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        assertNotNull(database.findByAddress("Address 2"));
        assertNotNull(database.findByPhone("Phone 5"));
        assertNotNull(database.findByName("First"));
        database.delete(1);
        assertThat(database.findByPhone("Phone 1")).isEmpty();
    }

    @Test
    void testReadWriteDatabase() {
        PersonDatabase database = new ReadWriteDatabase();

        Person person1 = new Person(1, "First", "Address 1", "Phone 1");
        Person person2 = new Person(2, "Second", "Address 2", "Phone 2");
        Person person3 = new Person(3, "Third", "Address 3", "Phone 3");
        Person person4 = new Person(4, "yaFirst", "Address 4", "Phone 4");
        Person person5 = new Person(5, "yaSecond", "Address 5", "Phone 5");
        Person person6 = new Person(6, "yaThird", "Address 6", "Phone 6");

        List<Person> people = List.of(
            person1, person2, person3, person4, person5, person6
        );

        List<Thread> addThreads = new ArrayList<>();
        people.forEach(person -> {
                addThreads.add(new Thread(() -> database.add(person)));
            }
        );
        addThreads.forEach(Thread::start);

        addThreads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        assertNotNull(database.findByAddress("Address 2"));
        assertNotNull(database.findByPhone("Phone 5"));
        assertNotNull(database.findByName("First"));
        database.delete(1);
        assertThat(database.findByPhone("Phone 1")).isEmpty();
    }
}
