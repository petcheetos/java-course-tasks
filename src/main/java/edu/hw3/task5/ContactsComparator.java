package edu.hw3.task5;

import java.util.Comparator;

public class ContactsComparator implements Comparator<Contact> {

    @Override
    public int compare(Contact leftContact, Contact rightContact) {
        String leftSurname = leftContact.getSurname();
        String rightSurname = rightContact.getSurname();

        if (leftSurname != null && rightSurname != null) {
            return leftSurname.compareTo(rightSurname);
        } else if (leftSurname != null && rightSurname == null) {
            return leftSurname.compareTo(rightContact.getName());
        } else if (leftSurname == null && rightSurname != null) {
            return leftContact.getName().compareTo(rightSurname);
        }

        String leftName = leftContact.getName();
        String rightName = rightContact.getName();
        return leftName.compareTo(rightName);
    }
}
