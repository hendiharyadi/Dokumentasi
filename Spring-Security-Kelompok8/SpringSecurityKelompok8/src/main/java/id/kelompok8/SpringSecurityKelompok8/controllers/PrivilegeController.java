package id.kelompok8.SpringSecurityKelompok8.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.kelompok8.SpringSecurityKelompok8.models.entity.Privilege;
import id.kelompok8.SpringSecurityKelompok8.services.PrivilegeService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

@AllArgsConstructor
@RestController
@RequestMapping("privilege")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class PrivilegeController {
    
    private PrivilegeService privilegeService;
    
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public List<Map<String, Object>> getAllMap(){
        return privilegeService.getAllMap();
    }
    
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("dashboard")
    public String dashboard() {
        return "<p style=\"color:blue;\">Welcome to ROLE Dashboard</p>";
    }
    
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping("landing-page")
    public String landingPage() {
        return "<p style=\"color:blue;\">Welcome to ROLE Landing Page</p>";
    }
    
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public Privilege insert(@RequestBody Privilege privilege){
        return privilegeService.insert(privilege);
    }
    
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping
    public Privilege update(@RequestBody Privilege privilege){
        return privilegeService.update(privilege);
    }
    
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("{id}")
    public String delete(@PathVariable Integer id){
        return privilegeService.deleteById(id);
    }
}
