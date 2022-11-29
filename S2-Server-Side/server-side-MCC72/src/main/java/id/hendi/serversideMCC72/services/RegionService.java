/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.services;

import id.hendi.serversideMCC72.models.entity.Region;
import id.hendi.serversideMCC72.repositories.RegionRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Hendi
 */
@Service
public class RegionService {
    
    private RegionRepository regionRepository;
    
    @Autowired
    public RegionService(RegionRepository regionRepository){
        this.regionRepository = regionRepository;
    }
    
    public List<Region> findAll(){
        if (regionRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data available");
        }
        return regionRepository.findAll();
    }
    
    public Region findById(Integer id){
        if (!regionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data is not exist");
        }
        return regionRepository.findById(id).get();
    }
    
    public Region insert(Region r) {
        if (regionRepository.existsById(r.getId())) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Data is exist!!!");
        }
        return regionRepository.save(r);
    }

    public Region update(Region r) {
        if(!regionRepository.existsById(r.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data is not exist");
        }
        
        return regionRepository.save(r);
    }
    
    public String deleteById(Integer id) {
        if (!regionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data is not exist");
        }
        Region r = findById(id);
        regionRepository.deleteById(id);
        return "Delete for " + r.getName() + "success!!!";
    }
    
        public List<Map<String, Object>> getAll3() {
//        List<Map<String, Object>> list = new ArrayList<>();
//        List<Region> list1 = rr.findAll();

//        for (Region region : rr.findAll()) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("id", region.getId());
//            map.put("name", region.getName());
//            map.put("countryQty", region.getCountries().size());
//            list.add(map);
//        }
//        return list;

        return regionRepository.findAll().stream().map(region -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", region.getId());
            m.put("name", region.getName());
            m.put("countryQty", region.getCountries().size());
            return m;
        }).collect(Collectors.toList());
//        return list;
    }
}
