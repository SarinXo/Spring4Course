/*
package com.example.demo.config;

import com.example.demo.dto.Message;
import com.example.demo.dto.Person;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class H2Configuration {

    @Bean
    public CommandLineRunner dataInitializer(MessageRepository messageRepository,
                                             PersonRepository personRepository) {
        List<Person> persons = new ArrayList<>(){{
            add(Person.builder().firstname("firstname1").surname("surname1").lastname("lastname1").birthday(LocalDate.now()).build());
            add(Person.builder().firstname("firstname2").surname("surname2").lastname("lastname2").birthday(LocalDate.now()).build());
            add(Person.builder().firstname("firstname3").surname("surname3").lastname("lastname3").birthday(LocalDate.now()).build());
            add(Person.builder().firstname("firstname4").surname("surname4").lastname("lastname4").birthday(LocalDate.now()).build());
            add(Person.builder().firstname("firstname5").surname("surname5").lastname("lastname5").birthday(LocalDate.now()).build());
        }};

        List<Message> messages = new ArrayList<>(){{
            add(Message.builder().title("title1").text("text1").time(LocalDateTime.now()).person(persons.get(1)).build());
            add(Message.builder().title("title2").text("text2").time(LocalDateTime.now()).person(persons.get(1)).build());
            add(Message.builder().title("title3").text("text3").time(LocalDateTime.now()).person(persons.get(1)).build());
            add(Message.builder().title("title4").text("text4").time(LocalDateTime.now()).person(persons.get(2)).build());
            add(Message.builder().title("title5").text("text5").time(LocalDateTime.now()).person(persons.get(2)).build());
            add(Message.builder().title("title6").text("text6").time(LocalDateTime.now()).person(persons.get(3)).build());
            add(Message.builder().title("title7").text("text7").time(LocalDateTime.now()).person(persons.get(4)).build());
            add(Message.builder().title("title8").text("text8").time(LocalDateTime.now()).person(persons.get(3)).build());
        }};

        return args -> {
            personRepository.saveAll(persons);
            messageRepository.saveAll(messages);
        };
    }

}
*/
