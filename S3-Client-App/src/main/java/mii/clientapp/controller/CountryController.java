/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.controller;

import lombok.AllArgsConstructor;
import mii.clientapp.models.Country;
import mii.clientapp.models.CountryDTO;
import mii.clientapp.service.CountryService;
import mii.clientapp.service.RegionService;
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
@RequestMapping("/country")
@AllArgsConstructor
public class CountryController {
    
    private CountryService countryService;
    private RegionService regionService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("countries", countryService.getAll());
        return "country/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("country", countryService.getById(id));
        return "country/detail";
    }
    
    @GetMapping("/create")
    public String createView(CountryDTO country, Model model) {
        model.addAttribute("regions", regionService.getAll());
        return "country/create-form";
    }

    @PostMapping
    public String create(CountryDTO country) {
        countryService.create(country);
        return "redirect:/country";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable int id, Country country, Model model) {
        model.addAttribute("country", countryService.getById(id));
        model.addAttribute("regions", regionService.getAll());
        return "country/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, CountryDTO country) {
        countryService.update(id, country);
        return "redirect:/country";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        countryService.delete(id);
        return "redirect:/country";
    }
}
