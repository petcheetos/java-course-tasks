package edu.hw3.task5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class ContactsParser {

    private ContactsParser() {
    }

    public static Contact[] parseContacts(String[] names, String sortOrder) {
        if (names != null) {
            Contact[] contacts = new Contact[names.length];
            for (int i = 0; i < names.length; i++) {
                Contact currContact = split(names[i]);
                contacts[i] = currContact;
            }
            if (Objects.equals(sortOrder, "ASC")) {
                return sortAscendingOrder(contacts);
            } else if (Objects.equals(sortOrder, "DESC")) {
                return sortDescendingOrder(contacts);
            }
        }
        return new Contact[0];
    }

    private static Contact[] sortAscendingOrder(Contact[] contacts) {
        Arrays.sort(contacts, new ContactsComparator());
        return contacts;
    }

    private static Contact[] sortDescendingOrder(Contact[] contacts) {
        Arrays.sort(contacts, Collections.reverseOrder(new ContactsComparator()));
        return contacts;
    }

    private static Contact split(String fullname) {
        String[] splitedName = fullname.split(" ");
        if (splitedName.length == 2) {
            return new Contact(splitedName[0], splitedName[1]);
        } else if (splitedName.length == 1) {
            return new Contact(splitedName[0]);
        }
        return new Contact();
    }
}
