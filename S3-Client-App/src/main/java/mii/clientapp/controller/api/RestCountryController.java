/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.controller.api;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.clientapp.models.Country;
import mii.clientapp.models.CountryDTO;
import mii.clientapp.service.CountryService;
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
@RequestMapping("/api/country")
@AllArgsConstructor
public class RestCountryController {
    
    private CountryService countryService;

    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public Country getAll(@PathVariable int id) {
        return countryService.getById(id);
    }

    @PostMapping
    public Country create(@RequestBody CountryDTO country){
        return countryService.create(country);
    }

    @PutMapping("/{id}")
    public Country update(@PathVariable int id, @RequestBody CountryDTO country){
        return countryService.update(id, country);
    }

    @DeleteMapping("/{id}")
    public Country delete(@PathVariable int id){
        return countryService.delete(id);
    }
}
