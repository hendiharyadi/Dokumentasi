/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.controllers;

import id.hendi.serversideMCC72.models.entity.History;
import id.hendi.serversideMCC72.services.HistoryService;
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
@RequestMapping("history")
public class HistoryController {
    
    private HistoryService historyService;
    
    @Autowired
    public HistoryController(HistoryService historyService){
        this.historyService = historyService;
    }
    
    @GetMapping("test")
    public String test (){
        return "Hai";
    }
    
    @GetMapping
    public List<History> getAll(){
        List<History> countries = historyService.findAll();
        return countries;
    }
    
    @GetMapping("save")
    public History save(){
        History history = new History(1, null, null, null, null, null);
        return historyService.save(history);
    }
    
    @GetMapping("id")
    public History getReferenceById(){
        History history = historyService.getReferenceById(1);
        return history;
    }
}
