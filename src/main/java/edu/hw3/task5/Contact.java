package edu.hw3.task5;

public class Contact {
    private String name = null;
    private String surname = null;

    public Contact() {
    }

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Contact(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }
}
