package edu.hw3.task5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactsParserTest {
    @Test
    void testParseContactsAscendingSortWithFullNames() {
        String[] names = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String key = "ASC";
        Contact[] contactsSorted = new Contact[] {
            new Contact("Thomas", "Aquinas"),
            new Contact("Rene", "Descartes"),
            new Contact("David", "Hume"),
            new Contact("John", "Locke")
        };
        Contact[] contacts = ContactsParser.parseContacts(names, key);
        for (int i = 0; i < contacts.length; i++) {
            assertEquals(contacts[i].getName(), contactsSorted[i].getName());
            assertEquals(contacts[i].getSurname(), contactsSorted[i].getSurname());
        }
    }

    @Test
    void testParseContactsAscendingSortWithOnlyName() {
        String[] names = new String[] {"John Locke", "Thomas Aquinas", "David", "Rene Descartes"};
        String key = "ASC";
        Contact[] contactsSorted = new Contact[] {
            new Contact("Thomas", "Aquinas"),
            new Contact("David"),
            new Contact("Rene", "Descartes"),
            new Contact("John", "Locke")
        };
        Contact[] contacts = ContactsParser.parseContacts(names, key);
        for (int i = 0; i < contacts.length; i++) {
            assertEquals(contacts[i].getName(), contactsSorted[i].getName());
            assertEquals(contacts[i].getSurname(), contactsSorted[i].getSurname());
        }
    }

    @Test
    void testParseContactsDescendingSortWithFullNames() {
        String[] names = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String key = "DESC";
        Contact[] contactsSorted = new Contact[] {
            new Contact("John", "Locke"),
            new Contact("David", "Hume"),
            new Contact("Rene", "Descartes"),
            new Contact("Thomas", "Aquinas"),
        };
        Contact[] contacts = ContactsParser.parseContacts(names, key);
        for (int i = 0; i < contacts.length; i++) {
            assertEquals(contacts[i].getName(), contactsSorted[i].getName());
            assertEquals(contacts[i].getSurname(), contactsSorted[i].getSurname());
        }
    }

    @Test
    void testParseContactsDescendingSortWithOnlyName() {
        String[] names = new String[] {"John Locke", "Thomas Aquinas", "David", "Rene Descartes"};
        String key = "DESC";
        Contact[] contactsSorted = new Contact[] {
            new Contact("John", "Locke"),
            new Contact("Rene", "Descartes"),
            new Contact("David"),
            new Contact("Thomas", "Aquinas")
        };
        Contact[] contacts = ContactsParser.parseContacts(names, key);
        for (int i = 0; i < contacts.length; i++) {
            assertEquals(contacts[i].getName(), contactsSorted[i].getName());
            assertEquals(contacts[i].getSurname(), contactsSorted[i].getSurname());
        }
    }

    @Test
    void testParseContactsWithEmptyArrays() {
        String[] names = new String[] {};
        String key = "DESC";
        Contact[] contactsSorted = new Contact[] {};
        Contact[] contacts = ContactsParser.parseContacts(names, key);
        assertArrayEquals(contacts, contactsSorted);
    }

    @Test
    void testParseContactsWithNullArray() {
        String[] names = null;
        String key = "DESC";
        Contact[] contactsSorted = new Contact[] {};
        Contact[] contacts = ContactsParser.parseContacts(names, key);
        assertArrayEquals(contacts, contactsSorted);
    }
}
