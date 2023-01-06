/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.controller;

import lombok.AllArgsConstructor;
import mii.clientapp.models.Location;
import mii.clientapp.models.LocationDTO;
import mii.clientapp.service.CountryService;
import mii.clientapp.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Hendi
 */
@Controller
@RequestMapping("/location")
@AllArgsConstructor
public class LocationController {
    
    private LocationService locationService;
    private CountryService countryService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("locations", locationService.getAll());
        return "location/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("location", locationService.getById(id));
        return "location/detail";
    }
    
    @GetMapping("/create")
    public String createView(LocationDTO location, Model model) {
        model.addAttribute("countries", countryService.getAll());
        return "location/create-form";
    }

    @PostMapping
    public String create(LocationDTO location) {
        locationService.create(location);
        return "redirect:/location";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable int id, Location location, Model model) {
        model.addAttribute("location", locationService.getById(id));
        model.addAttribute("countries", countryService.getAll());
        return "location/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, LocationDTO location) {
        locationService.update(id, location);
        return "redirect:/location";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        locationService.delete(id);
        return "redirect:/location";
    }
}
