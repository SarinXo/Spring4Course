package com.example.demo.service;

import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Person addMessage(int id, Message message) {
        personRepository.findById(id).ifPresent(person ->{
            message.setPerson(person);
            message.setTime(LocalDateTime.now());
            person.addMessage(message);
        });

        return personRepository.save(person);
    }
}
