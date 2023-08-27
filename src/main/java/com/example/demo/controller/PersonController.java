package com.example.demo.controller;

import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import com.example.demo.service.PersonService;
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

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /*
    *       {
    *           "id": 9,
    *           "text": "333",
    *           "title": "dsfd",
    *           "time": "2023-08-27T19:38:17.665763"
    *       }
    *   examlpe
    */
    @PostMapping("/persons/{id}/message")
    public ResponseEntity<?> addMessage(@PathVariable int id, @RequestBody Message message) {
        Person person = personService.saveMessage(id, message);
        // по хорошему все-таки сделать обработчик ошибок...
        return  !person.isEmpty() ?
                new ResponseEntity<>(person , HttpStatus.OK)
                : new ResponseEntity<>("такого пользователя не существует!" , HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/persons/{id}/message")
    public ResponseEntity<?> deleteMessage(@PathVariable int id, @RequestBody Message message) {
        return  personService.deleteMessage(id, message) ?
                new ResponseEntity<>(message , HttpStatus.OK)
                : new ResponseEntity<>("Такого сообщения нет у пользователя!" , HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/persons/{id}/all_messages")
    public ResponseEntity<List<Message>> getAllMessages(@PathVariable int id){
        List<Message> messages = personService.getAllMessages(id);
        var status = messages.isEmpty() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(messages, status);
    }

}
