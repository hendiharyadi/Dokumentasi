/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.services;

import id.hendi.serversideMCC72.models.entity.Job;
import id.hendi.serversideMCC72.repositories.JobRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hendi
 */
@Service
public class JobService {
    
    private JobRepository jobRepository;
    
    @Autowired
    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }
    
    public List<Job> findAll(){
        return jobRepository.findAll();
    }
    
    public Job save(Job c){
        return jobRepository.save(c);
    }
    
    public Job getReferenceById(Integer id){
        return jobRepository.getReferenceById(id);
    }
    
}
