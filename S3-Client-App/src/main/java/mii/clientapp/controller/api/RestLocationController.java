/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.controller.api;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.clientapp.models.Location;
import mii.clientapp.models.LocationDTO;
import mii.clientapp.service.LocationService;
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
@RequestMapping("/api/location")
@AllArgsConstructor
public class RestLocationController {
    
    private LocationService locationService;

    @GetMapping
    public List<Location> getAll() {
        return locationService.getAll();
    }

    @GetMapping("/{id}")
    public Location getAll(@PathVariable int id) {
        return locationService.getById(id);
    }

    @PostMapping 
    public Location create(@RequestBody LocationDTO location){
        return locationService.create(location);
    }

    @PutMapping("/{id}")
    public Location update(@PathVariable int id, @RequestBody LocationDTO location){
        return locationService.update(id, location);
    }

    @DeleteMapping("/{id}")
    public Location delete(@PathVariable int id){
        return locationService.delete(id);
    }
}
