package com.example.demo.service;

import com.example.demo.dto.Message;
import com.example.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Optional<Message> getById(int id){
        return messageRepository.findById(id);
    }

    public List<Message> getAllMessages(){
        return ((List<Message>) messageRepository.findAll());
    }

    public void deleteMessage(int id){
        messageRepository.deleteById(id);
    }

    public Message addMessage(Message message){
        return messageRepository.save(message);
    }

    public void updateMessage(Message message){
        messageRepository.save(message);
    }
}
