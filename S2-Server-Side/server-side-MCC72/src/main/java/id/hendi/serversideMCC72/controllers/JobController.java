/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.controllers;

import id.hendi.serversideMCC72.models.entity.Job;
import id.hendi.serversideMCC72.services.JobService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hendi
 */
@RestController
@RequestMapping("job")
public class JobController {
    
    private JobService jobService;
    
    @Autowired
    public JobController(JobService jobService){
        this.jobService = jobService;
    }
    
    @GetMapping("test")
    public String test (){
        return "Hai";
    }
    
    @GetMapping
    public List<Job> getAll(){
        List<Job> countries = jobService.findAll();
        return countries;
    }
    
    @GetMapping("save")
    public Job save(){
        Job job = new Job(1, "RD", "Research and Development", 50505050, 80808080, null, null);
        return jobService.save(job);
    }
    
    @GetMapping("id")
    public Job getReferenceById(){
        Job job = jobService.getReferenceById(1);
        return job;
    }
    
}
