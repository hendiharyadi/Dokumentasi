/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.controllers;

import id.hendi.serversideMCC72.models.entity.Location;
import id.hendi.serversideMCC72.services.LocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendi
 */
@RestController
@RequestMapping("location")
public class LocationController {
    
    private LocationService locationService;
    
    @Autowired
    public LocationController(LocationService locationService){
        this.locationService = locationService;
    }
    
    @GetMapping("test")
    public String test (){
        return "Hai";
    }
    
    @GetMapping
    public List<Location> getAll(){
        List<Location> countries = locationService.findAll();
        return countries;
    }
    
    @GetMapping("save")
    public Location save(){
        Location location = new Location(1, "Jatihandap St.", "40193", "Bandung", "West Java", null, null);
        return locationService.save(location);
    }
    
    @GetMapping("id")
    public Location getReferenceById(){
        Location location = locationService.getReferenceById(1);
        return location;
    }
    
}
