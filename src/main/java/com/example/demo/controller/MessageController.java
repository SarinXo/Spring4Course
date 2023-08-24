package com.example.demo.controller;

import com.example.demo.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *  Create - http://localhost:8080/message
 *  Read   - http://localhost:8080/message/{id}
 *  Update - http://localhost:8080/message/update/{id}
 *  Delete - http://localhost:8080/messages/delete/{id}
 *
 *  request body sample:
 *  {
 *     "id": 7,
 *     "text": "test",
 *     "time": "2023-08-23T20:00:00.665763"
 * }
 *
 * headers
 *      Key:   Content-Type
 *      Value: application/json
 *
 * body: raw
 */
@RestController
public class MessageController {
    //представим, что это соединение с бд
    private final List<Message> messages = new ArrayList<>(){{
        add(new Message(1, "message1", LocalDateTime.now()));
        add(new Message(2, "message2", LocalDateTime.now()));
        add(new Message(3, "message3", LocalDateTime.now()));
        add(new Message(4, "message4", LocalDateTime.now()));
        add(new Message(5, "message5", LocalDateTime.now()));
        add(new Message(6, "message6", LocalDateTime.now()));
    }};

    //CREATE
    @PostMapping("/message")
    public ResponseEntity<Message> addMessage(@RequestBody Message message){
        messages.add(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //READ
    @GetMapping("/message/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable int id){
        return messages.stream()
                .filter(m ->  m.getId() == id)
                .findFirst()
                .map(m -> new ResponseEntity<>(m, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Message(), HttpStatus.NOT_FOUND));
    }

    //UPDATE
    @PutMapping("/message/update/{id}")
    public ResponseEntity<Message>  updateMessage(@PathVariable int id, @RequestBody Message message) {
        boolean isFind = false;
        for(Message m: messages){
            if(m.getId() == id){
                int position = messages.indexOf(m);
                messages.set(position, message);
                isFind = true;
                //по сути id - уникальный, а значит нет смысла дальше идти по коллекции?
                //момент по поводу @PathVa
                //break;
            }
        }
        return isFind ?
                  new ResponseEntity<>(message, HttpStatus.OK)
                : addMessage(message);
    }

    //DELETE
    @DeleteMapping("/messages/delete/{id}")
    public void deleteMessage(@PathVariable int id) {
        messages.removeIf(m -> m.getId() == id);
    }
}
