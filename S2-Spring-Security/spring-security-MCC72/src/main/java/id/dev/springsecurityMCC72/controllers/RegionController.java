/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dev.springsecurityMCC72.controllers;

import id.dev.springsecurityMCC72.models.Region;
import id.dev.springsecurityMCC72.services.RegionService;
import java.util.List;
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
@RequestMapping("/region")
@PreAuthorize("hasRole('ROLE_USER')")
public class RegionController {
    
    private RegionService regionService;
    
    @Autowired
    public RegionController(RegionService regionService){
        this.regionService = regionService;
    }
    
    @GetMapping
    public List<Region> getAll(){
        List<Region> regions = regionService.getAll();
        return regions;
    }
    
    @PostMapping
    public Region insert(@RequestBody Region region){
        return regionService.create(region);
    }
    
    @PutMapping("/{id}")
    public Region update(@PathVariable Integer id, @RequestBody Region region){
        return regionService.update(id, region);
    }
    
    @DeleteMapping("/{id}")
    public Region deleteById(@PathVariable Integer id){
        return regionService.delete(id);
    }
    
    @GetMapping("/{id}")
    public Region getById(@PathVariable Integer id){
        Region region = regionService.getById(id);
        return region;
    }
    
}
