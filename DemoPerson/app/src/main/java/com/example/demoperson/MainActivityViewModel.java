package com.example.demoperson;

import androidx.lifecycle.ViewModel;

import com.example.demoperson.models.Person;
import com.example.demoperson.repo.PersonRepository;

public class MainActivityViewModel extends ViewModel {
    private final PersonRepository personRepository;

    public MainActivityViewModel(){
        personRepository = new PersonRepository();
    }

    public Person[] getPersons(String name){
        return personRepository.getPersons(name);
    }
}
