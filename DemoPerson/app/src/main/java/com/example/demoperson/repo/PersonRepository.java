package com.example.demoperson.repo;

import com.example.demoperson.datasource.PersonDemoData;
import com.example.demoperson.models.Person;

public class PersonRepository {
    public Person[] getPersons(String name) {
        return PersonDemoData.findPersonByName(name);
    }
}
