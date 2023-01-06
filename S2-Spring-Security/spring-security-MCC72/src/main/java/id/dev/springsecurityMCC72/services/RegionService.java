/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dev.springsecurityMCC72.services;

import id.dev.springsecurityMCC72.models.Region;
import id.dev.springsecurityMCC72.repositories.RegionRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Hendi
 */
@Service
@AllArgsConstructor
public class RegionService {

    private RegionRepository regionRepository;

    public List<Region> getAll() {
        return regionRepository.findAll();
    }
    
    public Region getById(int id){
        return regionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Region not foudn!"));
    }
    
    public Region create(Region region){
        return regionRepository.save(region);
    }
    
    public Region update(int id, Region region){
        getById(id);
        region.setId(id);
        return regionRepository.save(region);
    }
    
    public Region delete(int id){
        Region region = getById(id);
        regionRepository.delete(region);
//        regionRepository.deleteById(id);
        return region;
    }

}