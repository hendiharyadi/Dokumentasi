/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.services;

import id.hendi.serversideMCC72.models.entity.Person;
import id.hendi.serversideMCC72.repositories.PersonRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Hendi
 */
@Service
@AllArgsConstructor
public class PersonService {
    
    private PersonRepository personRepository;
    
    public List<Person> getAll(){
        if (personRepository.findAll().isEmpty()){
            throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Data not found!!!");
        }
        return personRepository.findAll();
    }
    
    public Person getById(Integer id){
        return personRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found!!!"));
    }
    
    public Person create(Person person){
        if (person.getId()!= null){
            throw new ResponseStatusException(
            HttpStatus.FOUND, "Data is already existed...");
        }
        if (personRepository.findByUsername(person.getUsername()).isPresent()){
            throw new ResponseStatusException(
            HttpStatus.FOUND, "Username is already existed..."
            );
        }
        return personRepository.save(person);
    }
    
    public Person update(Integer id, Person person){
        getById(id);
        person.setId(id);
        return personRepository.save(person);
    }
    
    public Person delete(Integer id){
        Person person = getById(id);
        personRepository.delete(person);
        return person;
    }
    
    //JPQL
    public List<Person> findByAgeJPQL(Integer age){
        return personRepository.findByAgeJPQL(age);
    }
    
    public Person findByFullNameJPQL(String fullName){
        return personRepository.findByFullNameJPQL(fullName);
    }
    
    //Native SQL
    public List<Person> findByAgeNative(Integer age){
        return personRepository.findByAgeNative(age);
    }
    
    public List<Person> findByFullNameNative(String fullName){
        return personRepository.findByFullNameNative("%" + fullName + "%");
    }
    
    //Query Method
    public Person findByEmail(String email){
        return personRepository.findByEmail(email);
    }
    
    public List<Person> findByFullName(String fullName){
        return personRepository.findByFullNameContainsOrderByIdDesc(fullName);
    }
    
    public List<Person> findByFullNameStart(String fullName){
        return personRepository.findByFullNameStartingWith(fullName);
    }
    
    public List<Person> findByFullNameOrEmail(String fullName, String email){
        return personRepository.findByFullNameContainsOrEmailContains(fullName, email);
    }
}
