/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.service;

import java.util.List;
import mii.clientapp.models.Location;
import mii.clientapp.models.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author MSI-JO
 */
@Service
public class LocationService {

    private RestTemplate restTemplate;
    
    @Autowired
    public LocationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Value("${server.baseUrl}/location")
    private String url;

    public List<Location> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Location>>() {
                }).getBody();
    }

    public Location getById(int id) {
        return restTemplate.exchange(url+ "/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<Location>() {
                }).getBody();
    }

    public Location create(LocationDTO location) {
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(location),
                new ParameterizedTypeReference<Location>() {
                }).getBody();
    }

    public Location update(int id, LocationDTO location) {
        return restTemplate.exchange(url + "/" + id, HttpMethod.PUT, new HttpEntity(location),
                new ParameterizedTypeReference<Location>() {
                }).getBody();
    }

    public Location delete(int id) {
        return restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Location>() {
                }).getBody();
    }
}
