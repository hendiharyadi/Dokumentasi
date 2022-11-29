/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.controllers;

import id.hendi.serversideMCC72.models.dto.request.CountryDTO;
import id.hendi.serversideMCC72.models.entity.Country;
import id.hendi.serversideMCC72.services.CountryService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }
    
    //    localhost:8081/api/country
    @GetMapping
    public List<Country> getAll(){
        return countryService.findAll();
    }
    
//    localhost:8081/api/country/1
    @GetMapping("{id}")
    public Country getById(@PathVariable Integer id){
        return countryService.findById(id);
    }
    
//    localhost:8081/api/country
    @PostMapping
    public Country insert(@RequestBody CountryDTO country){
        return countryService.insert(country);
    }
    
//    localhost:8081/api/country
    @PutMapping
    public Country update(@RequestBody Country country){
        return countryService.update(country);
    }
    
    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id){
        return countryService.deleteById(id);
    }
    
    @GetMapping("get")
    public List<Map<String, Object>> getAll2(){
        List<Map<String, Object>> list = new ArrayList<>();
        
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "A sia");
        list.add(map);
        
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 2);
        map2.put("name", "A Anjeun");
        list.add(map2);
        
//        return list;
        return countryService.getAll3();
    }
    
     @PostMapping("/model-mapper")
    public Country create(@RequestBody CountryDTO country){
        return countryService.create(country);
    }
}
