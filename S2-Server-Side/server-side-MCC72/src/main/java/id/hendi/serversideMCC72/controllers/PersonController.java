/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.controllers;

import id.hendi.serversideMCC72.models.dto.request.SearchDataRequest;
import id.hendi.serversideMCC72.models.entity.Person;
import id.hendi.serversideMCC72.services.PersonService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendi
 */
@RestController
@RequestMapping("person")
@AllArgsConstructor
public class PersonController {
    
    private PersonService personService;
    
    @GetMapping
    public List<Person> getAll(){
        return personService.getAll();
    }
    
    @GetMapping("/{id}")
    public Person getById(@PathVariable Integer id){
        return personService.getById(id);
    }
    
    @PostMapping
    public Person insert(@RequestBody Person person){
        return personService.create(person);
    }
    
    @PutMapping("/{id}")
    public Person update(@PathVariable Integer id, @RequestBody Person person){
        return personService.update(id, person);
    }
    
    @DeleteMapping("/{id}")
    public Person delete (@PathVariable Integer id){
        return personService.delete(id);
    }
    
    //localhost:8081/api/person/search/age = path
    //localhost:8081/api/person/search?age=20 = param
    
    //JPQL
    @GetMapping("/search-jpql")
    public List <Person> findByAgeJPQL(@RequestParam(name = "age") Integer age){
        return personService.findByAgeJPQL(age);
    }
    
    @PostMapping("/search-jpql")
    public Person findByFullNameJPQL(@RequestBody SearchDataRequest searchData){
        return personService.findByFullNameJPQL(searchData.getSearchKey());
    }
    
    //Native SQL
    @GetMapping("/search-native")
    public List<Person> findByAgeNative(@RequestParam(name = "age") Integer age){
        return personService.findByAgeNative(age);
    }
    
    @PostMapping("/search-native")
    public List<Person> findByFullNameNative(@RequestBody SearchDataRequest searchData){
        return personService.findByFullNameNative(searchData.getSearchKey());
    }
    
    //Query Method
    @PostMapping("/find-email")
    public Person findByEmail(@RequestBody SearchDataRequest searchData){
        return personService.findByEmail(searchData.getSearchKey());
    }
    
    @PostMapping("/find-fullname")
    public List<Person> findByFullName(@RequestBody SearchDataRequest searchData){
        return personService.findByFullName(searchData.getSearchKey());
    }
    
    @PostMapping("/find-fullname/start")
    public List<Person> findByFullNameStart(@RequestBody SearchDataRequest searchData){
        return personService.findByFullNameStart(searchData.getSearchKey());
    }
    
    @PostMapping("/find-fullname-email")
    public List<Person> findByFullNameOrEmail(@RequestBody SearchDataRequest searchData){
        return personService.findByFullNameOrEmail(searchData.getSearchKey(), searchData.getOtherKey());
    }
}
