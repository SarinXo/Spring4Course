package com.example.demo.controller;

import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import com.example.demo.service.PersonService;
import jakarta.persistence.JoinColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class PersonController {

    PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/persons/{id}/messages")
    public ResponseEntity<?> addMessage(@PathVariable int id, @RequestBody Message message) {
        Person person = personService.addMessage(id, message);
        return  person.isEmpty() ?
                new ResponseEntity<>("такого пользователя не существует!" , HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(person , HttpStatus.OK);
    }

    @DeleteMapping("/persons/{id}/message")
    public ResponseEntity<?> deleteMessage(@PathVariable int id, @RequestBody Message message) {
        return  personService.deleteMessage(id, message) ?
                new ResponseEntity<>(message , HttpStatus.OK)
                : new ResponseEntity<>("Такого сообщения нет у пользователя!" , HttpStatus.BAD_REQUEST);
    }
@JoinColumn
    @GetMapping("/persons/{id}/all_messages")
    public ResponseEntity<List<Message>> getAllMessages(@PathVariable int id, @RequestBody Message message){
        return new ResponseEntity<>(personService.getAllMessages(id, message), HttpStatus.OK);
    }

}
