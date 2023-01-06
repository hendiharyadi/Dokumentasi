/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.dev.springsecurityMCC72.services;

import id.dev.springsecurityMCC72.models.Country;
import id.dev.springsecurityMCC72.models.Region;
import id.dev.springsecurityMCC72.models.dto.CountryDTO;
import id.dev.springsecurityMCC72.repositories.CountryRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Hendi
 */
@Service
@AllArgsConstructor
public class CountryService {
    private CountryRepository countryRepository;
    private RegionService regionService;
    private ModelMapper modelMapper;

    public List<Country> findAll(){
        return countryRepository.findAll();
    }
    public Country findById(Integer id){
        return countryRepository.findById(id).get();
    }
        // DTO without model mapper
    public Country insert(CountryDTO countryDTO){
        Country country = new Country();
        country.setCode(countryDTO.getCode());
        country.setName(countryDTO.getName());
        Region region = regionService.getById(countryDTO.getRegionId());
        country.setRegion(region);
        return countryRepository.save(country);

    }
    //DTO with model mapper
    public Country create(CountryDTO countryDTO){
        Country country = modelMapper.map(countryDTO, Country.class);
        country.setRegion(regionService.getById(countryDTO.getRegionId()));
        return countryRepository.save(country);
    }

    public Country update(Integer id, CountryDTO country){
//        if(!countryRepository.existsById(country.getId())){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Data is not exist");
//        }
        findById(id);
        Country c = new Country();
        c.setId(id);
        c.setCode(country.getCode());
        c.setName(country.getName());
        c.setRegion(regionService.getById(country.getRegionId()));
//        country.setId(id);
        return countryRepository.save(c);
    }
    public Country deleteById(Integer id) {
//        if (!countryRepository.existsById(id)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data is not exist");
//        }
//        Country country = findById(id);
        Country country = findById(id);
//        findById(id);
        countryRepository.deleteById(country.getId());
        return country;
//        return "Delete for " + country.getName() + "success!!!";
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

        return countryRepository.findAll().stream().map(c -> {
            Map<String, Object> m = new HashMap<>();
            m.put("id", c.getId());
            m.put("name", c.getCode());
            m.put("RegionID", c.getRegion().getName());
            return m;
        }).collect(Collectors.toList());
//        return list;
    }
}

