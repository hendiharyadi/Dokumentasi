/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.clientapp.service;

import java.util.List;
import mii.clientapp.models.Department;
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
public class DepartmentService {

    private RestTemplate restTemplate;
    
    @Autowired
    public DepartmentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Value("${server.baseUrl}/department")
    private String url;

    public List<Department> getAll() {
        return restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Department>>() {
                }).getBody();
    }

    public Department getById(int id) {
        return restTemplate.exchange(url+ "/" + id, HttpMethod.GET, null,
                new ParameterizedTypeReference<Department>() {
                }).getBody();
    }

    public Department create(Department department) {
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity(department),
                new ParameterizedTypeReference<Department>() {
                }).getBody();
    }

    public Department update(int id, Department department) {
        return restTemplate.exchange(url + "/" + id, HttpMethod.PUT, new HttpEntity(department),
                new ParameterizedTypeReference<Department>() {
                }).getBody();
    }

    public Department deleteById(int id) {
        return restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<Department>() {
                }).getBody();
    }
}
