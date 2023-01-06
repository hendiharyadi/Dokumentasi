package mii.clientapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Hendi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private int id;
    
    private String first_name;
    
    private String last_name;

    private String email;
    
    private String phone_number;
    
    private String hire_date;
    
    private int salary;
    
    private int commission_pct;
    
    private Employee manager;

    private Department department;
}