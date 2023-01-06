/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.service;

import java.util.List;
import lombok.AllArgsConstructor;
import mii.clientapp.models.Country;
import mii.clientapp.models.CountryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Hendi
 */
@Service
public class CountryService {
    
    private RestTemplate restTemplate;
    
    @Autowired
    public CountryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${server.baseUrl}/country")
    private String url;

    public List<Country> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Country>>() {
                }).getBody();
    }

    public Country getById(int id) {
        return restTemplate.exchange(url + "/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<Country>() {
                }).getBody();
    }

    public Country create(CountryDTO country) {
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(country),
                new ParameterizedTypeReference<Country>() {
                }).getBody();
    }

    public Country update(int id, CountryDTO country) {
        return restTemplate.exchange(url.concat("/" + id), HttpMethod.PUT, new HttpEntity(country),
                new ParameterizedTypeReference<Country>() {
                }).getBody();
    }

    public Country delete(int id) {
        return restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Country>() {
                }).getBody();
    }
}
