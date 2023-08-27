package com.example.demo.service;

import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    final PersonRepository personRepository;
    final MessageRepository messageRepository;
    @Autowired
    public PersonService(PersonRepository personRepository,
                         MessageRepository messageRepository ) {
        this.personRepository = personRepository;
        this.messageRepository = messageRepository;
    }

    public Person saveMessage(int id, Message message) {
        Optional<Person> oPerson = personRepository.findById(id);
        if(oPerson.isEmpty()){
            return new Person();
        }
        Person person = oPerson.get();
        message.setPerson(person);
        message.setTime(LocalDateTime.now());
        person.addMessage(message);
        return personRepository.save(person);
    }

    public boolean deleteMessage(int id, Message message){
        Optional<Person> person = personRepository.getPersonById(id);
        if(person.isEmpty())
            return false;

        List<Message> messages = person.get().getMessages().
                stream().filter(m -> m.equals(message)).toList();
        if(messages.isEmpty())
            return false;

        messageRepository.delete(message);
        return true;
    }

    public List<Message> getAllMessages(int id) {
        return personRepository.getPersonById(id)
                .map(Person::getMessages)
                .orElse(List.of());
    }

    public Optional<Person> getPersonById(int id){
        return personRepository.getPersonById(id);
    }
}
