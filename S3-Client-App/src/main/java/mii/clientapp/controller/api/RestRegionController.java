/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.controller.api;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.clientapp.models.Region;
import mii.clientapp.service.RegionService;
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
@RequestMapping("/api/region")
@AllArgsConstructor
public class RestRegionController {

    private RegionService regionService;

    @GetMapping
    public List<Region> getAll() {
        return regionService.getAll();
    }

    @GetMapping("/{id}")
    public Region getAll(@PathVariable int id) {
        return regionService.getById(id);
    }

    @PostMapping
    public Region create(@RequestBody Region region){
        return regionService.create(region);
    }

    @PutMapping("/{id}")
    public Region update(@PathVariable int id, @RequestBody Region region){
        return regionService.update(id, region);
    }

    @DeleteMapping("/{id}")
    public Region deleteById(@PathVariable int id){
        return regionService.deleteById(id);
    }

}