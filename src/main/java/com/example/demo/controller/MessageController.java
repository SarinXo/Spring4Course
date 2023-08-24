package com.example.demo.controller;

import com.example.demo.dto.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("/add")
    void initDB(){
        messageService.addMessage(new Message(1, "message1", LocalDateTime.now()));
        messageService.addMessage(new Message(2, "message2", LocalDateTime.now()));
        messageService.addMessage(new Message(3, "message3", LocalDateTime.now()));
        messageService.addMessage(new Message(4, "message4", LocalDateTime.now()));
        messageService.addMessage(new Message(5, "message5", LocalDateTime.now()));
        messageService.addMessage(new Message(6, "message6", LocalDateTime.now()));
    }

    //CREATE
    @PostMapping("/message")
    public ResponseEntity<Message> addMessage(@RequestBody Message message){
        return new ResponseEntity<>(messageService.addMessage(message), HttpStatus.CREATED);
    }

    //READ
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages(){
        var message = messageService.getAllMessages();
        var status = message.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(message, status);
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id){
        Optional<Message> message = messageService.getById(id);
        return message.map(
                        value -> new ResponseEntity<>(message.get(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new Message(), HttpStatus.NOT_FOUND));
    }

    //UPDATE
    @PutMapping("/message/update/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        //всё-таки меня напрягает, что id и message - передаются по-разному
        //если id будет отличаться от message.getId(); то будет работать не так как ожидается
        //поэтому лучше fail fast или еще лучше переработать логику функции
        //и вместо объекта message должна возвращаться ошибка, но это еще Exception Handler нужно писать + исключения
        if(id != message.getId()){
            return new ResponseEntity<>(new Message(-1, "ID в запросе и в объекте не совпадают!", LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        var status = messageService.getById(id).isPresent() ? HttpStatus.OK : HttpStatus.CREATED;
        // у меня изначально записи заменялись при обновлении.
        messageService.updateMessage(message);
        return new ResponseEntity<>(message, status);
    }

    //DELETE
    @DeleteMapping("/messages/delete/{id}")
    public void deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
    }



}
