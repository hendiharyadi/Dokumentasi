/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.hendi.serversideMCC72.models.dto.response;

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
public class EmailResponse {
    private String message;
    private boolean status;
}
