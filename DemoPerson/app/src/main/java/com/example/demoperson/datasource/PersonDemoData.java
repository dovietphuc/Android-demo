package com.example.demoperson.datasource;

import com.example.demoperson.models.Gender;
import com.example.demoperson.models.Person;

import java.util.Arrays;
import java.util.Date;

public class PersonDemoData {
    private static final Person[] PERSONS = {
            new Person("John",
                    new Date(2000, 1, 1),
                    Gender.MALE, "Ha Noi"),
            new Person("Eva",
                    new Date(2001, 1, 1),
                    Gender.FEMALE, "Ha Noi"),
            new Person("Lia",
                    new Date(2006, 1, 1),
                    Gender.FEMALE, "Ha Noi"),
            new Person("Alice",
                    new Date(2009, 1, 1),
                    Gender.OTHER, "Ha Noi")
    };

    public static Person[] findPersonByName(String name){
        if(name == null || name.trim().isEmpty()) {
            return PERSONS;
        }
        return Arrays.stream(PERSONS).filter(person -> {
            return person
                    .getName()
                    .toLowerCase()
                    .trim()
                    .contains(name.toLowerCase().trim());
        }).toArray(Person[]::new);
    }
}
