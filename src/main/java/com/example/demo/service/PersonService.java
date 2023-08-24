package com.example.demo.service;

import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addMessage(int id, Message message) {
        Person person = personRepository.findById(id).get();
        if(Objects.isNull(person)){
            return new Person();
        }
        message.setPerson(person);
        message.setTime(LocalDateTime.now());
        person.addMessage(message);
        return personRepository.save(person);
    }

    public boolean deleteMessage(int id, Message message){
        return personRepository.getPersonById(id).getMessages().remove(message);
    }


    public List<Message> getAllMessages(int id, Message message) {
        return personRepository.getPersonById(id).getMessages();
    }
}
