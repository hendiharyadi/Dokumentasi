/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.controllers;

import id.hendi.serversideMCC72.models.entity.Region;
import id.hendi.serversideMCC72.services.RegionService;
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
@RequestMapping("region")
public class RegionController {
    
    private RegionService regionService;
    
    @Autowired
    public RegionController(RegionService regionService){
        this.regionService = regionService;
    }
    
    @GetMapping("test")
    public String test(){
//        List<Region> regions = regionService.findAll();
//        for(Region region: regions){
//            System.out.println(region.getId());
//            System.out.println(region.getName());
//        }
        return "Welcome to Indonesia.";
    }
    
    //localhost:8081/api/region
    @GetMapping
    public List<Region> getAll(){
        List<Region> regions = regionService.findAll();
        return regions;
    }
    
    //localhost:8081/api/region
    @PostMapping
    public Region insert(@RequestBody Region region){
        return regionService.insert(region);
    }
    
    //localhost:8081/api/region
    @PutMapping
    public Region update(@RequestBody Region region){
        return regionService.update(region);
    }
    
    //localhost:8081/api/region/6
    @DeleteMapping("{id}")
    public String deleteById(@PathVariable Integer id){
        return regionService.deleteById(id);
    }
    
    //localhost:8081/api/region/1
    @GetMapping("{id}")
    public Region getById(@PathVariable Integer id){
        Region region = regionService.findById(id);
        return region;
    }
    
    //    localhost:8081/api/region/get
//    Region setara Map<String, Object> sama-sama Object
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
        return regionService.getAll3();
    }
}
