/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.services;

import id.hendi.serversideMCC72.models.entity.Location;
import id.hendi.serversideMCC72.repositories.LocationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hendi
 */
@Service
public class LocationService {
    
    private LocationRepository locationRepository;
    
    @Autowired
    public LocationService(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }
    
    public List<Location> findAll(){
        return locationRepository.findAll();
    }
    
    public Location save (Location l){
        return locationRepository.save(l);
    }
    
    public Location getReferenceById(Integer id){
        return locationRepository.getReferenceById(id);
    }
}
