package com.example.demo.controller;

import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PersonController {

    @PostMapping("/persons/{id}/messages")
    public Person addMessage(@PathVariable int id, @RequestBody Message message) {
        Person person = repository.findById(id).get();
        person.addMessage(message);
        return repository.save(person);
    }
}
