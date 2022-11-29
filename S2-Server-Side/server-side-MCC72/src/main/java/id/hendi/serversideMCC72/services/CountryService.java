/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.services;

import id.hendi.serversideMCC72.models.dto.request.CountryDTO;
import id.hendi.serversideMCC72.models.entity.Country;
import id.hendi.serversideMCC72.models.entity.Region;
import id.hendi.serversideMCC72.repositories.CountryRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Hendi
 */
@Service
public class CountryService {
    
    private CountryRepository countryRepository;
    private RegionService regionService;
    private ModelMapper modelMapper;
    
    @Autowired
    public CountryService(CountryRepository countryRepository, RegionService regionService, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.regionService = regionService;
        this.modelMapper = modelMapper;
    }
    

    public List<Country> findAll() {
        if (countryRepository.findAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data available");
        }
        return countryRepository.findAll();
    }


    public Country findById(Integer id) {
        if (!countryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data is not exist");
        }
        return countryRepository.findById(id).get();
    }

    public Country insert(CountryDTO country) {
//        if (countryRepository.existsById(r.getId())) {
//            throw new ResponseStatusException(HttpStatus.FOUND, "Data is exist!!!");
//        }
        Country c = new Country();
        c.setCode(country.getCode());
        c.setName(country.getName());
        
        Region region = regionService.findById(country.getRegionId());
        c.setRegion(region);
        return countryRepository.save(c);
    }
    
//     DTO with model mapper
    public Country create(CountryDTO countryDTO){
        if (countryRepository.existsByName(countryDTO.getName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Name is already exists...");
        }
        Country country = modelMapper.map(countryDTO, Country.class);
        country.setRegion(regionService.findById(countryDTO.getRegionId()));
        return countryRepository.save(country);
    }

    public Country update(Country country) {
        if(!countryRepository.existsById(country.getId())){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data is not exist");
        }
        
        return countryRepository.save(country);
    }

//    public Country save(Country r) {
//        return regionService.save(r);
//    }

    public String deleteById(Integer id) {
        if (!countryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data is not exist");
        }
        Country r = findById(id);
        countryRepository.deleteById(id);
        return "Delete for " + r.getName() + " success!!!";
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

        return countryRepository.findAll().stream().map(country -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", country.getId());
            m.put("name", country.getName());
            m.put("regionName", country.getRegion().getName());
            return m;
        }).collect(Collectors.toList());
//        return list;
    }
}
