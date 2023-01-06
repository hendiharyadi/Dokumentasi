/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dev.springsecurityMCC72.controllers;

import id.dev.springsecurityMCC72.models.Country;
import id.dev.springsecurityMCC72.models.dto.CountryDTO;
import id.dev.springsecurityMCC72.services.CountryService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendi
 */
@RestController
@RequestMapping("country")
public class CountryController {

     private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

//    localhost:8081/api/country
    @GetMapping
    public List<Country> getAll(){
        return countryService.findAll();
    }

    @GetMapping("/get")
     public List<Map<String, Object>> getAll3(){
         return countryService.getAll3(); 
     }

//    localhost:8081/api/country/1
    @GetMapping("/{id}")
    public Country getById(@PathVariable Integer id){
        return countryService.findById(id);
    }

//    localhost:8081/api/country
    @PostMapping
    public Country insert(@RequestBody CountryDTO country){
        return countryService.insert(country);
    }

    @PostMapping("/model-mapper")
    public Country create(@RequestBody CountryDTO country){
        return countryService.create(country);
    }

//    localhost:8081/api/country
    @PutMapping("/{id}")
    public Country update(@PathVariable Integer id, @RequestBody CountryDTO country){
        return countryService.update(id, country);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable Integer id){
        return countryService.deleteById(id);
    }
}
