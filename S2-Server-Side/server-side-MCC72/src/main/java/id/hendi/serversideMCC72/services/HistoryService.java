/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.services;

import id.hendi.serversideMCC72.models.entity.History;
import id.hendi.serversideMCC72.repositories.HistoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hendi
 */
@Service
public class HistoryService {
    
    private HistoryRepository historyRepository;
    
    @Autowired
    public HistoryService(HistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }
    
    public List<History> findAll(){
        return historyRepository.findAll();
    }
    
    public History save(History c){
        return historyRepository.save(c);
    }
    
    public History getReferenceById(Integer id){
        return historyRepository.getReferenceById(id);
    }
    
}
