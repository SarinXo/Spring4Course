package com.example.demo.repository;

import com.example.demo.dto.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> getAllBy();
    Optional<Person> getPersonById(int id);
}
