/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.models.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hendi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    
    private String to;
    
    private String name;
    
    private String subject;
    
    private String body;
    
    private String attach;
}
