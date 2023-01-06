/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.claintappsecurity.controller;

import lombok.AllArgsConstructor;
import mii.claintappsecurity.model.Region;
import mii.claintappsecurity.service.RegionService;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Controller // HTML
@RequestMapping("/region")
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_USER')")
public class RegionController {

    private RegionService regionService;
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("regions", regionService.getAll());
        return "region/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        model.addAttribute("region", regionService.getById(id));
        return "region/detail";
    }

    @GetMapping("/create")
    public String createView(Region region) {
        return "region/create-form";
    }

    @PostMapping
    public String create(Region region) {
        regionService.create(region);
        return "redirect:/region";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable int id, Region region, Model model) {
        model.addAttribute("region", regionService.getById(id));
        return "region/update-form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, Region region) {
        regionService.update(id, region);
        return "redirect:/region";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        regionService.deleteById(id);
        return "redirect:/region";
    }

}
