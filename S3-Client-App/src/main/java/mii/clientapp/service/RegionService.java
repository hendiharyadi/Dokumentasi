/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.service;

import java.util.List;
import mii.clientapp.models.Region;
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
public class RegionService {

    private RestTemplate restTemplate;
    
    @Autowired
    public RegionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Value("${server.baseUrl}/region")
    private String url;

    public List<Region> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Region>>() {
                }).getBody();
    }

    public Region getById(int id) {
        return restTemplate.exchange(url+ "/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<Region>() {
                }).getBody();
    }

    public Region create(Region region) {
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(region),
                new ParameterizedTypeReference<Region>() {
                }).getBody();
    }

    public Region update(int id, Region region) {
        return restTemplate.exchange(url + "/" + id, HttpMethod.PUT, new HttpEntity(region),
                new ParameterizedTypeReference<Region>() {
                }).getBody();
    }

    public Region deleteById(int id) {
        return restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Region>() {
                }).getBody();
    }
}
