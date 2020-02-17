package fr.rbo.elitapi.controller;

import fr.rbo.elitapi.exceptions.NotFoundException;
import fr.rbo.elitapi.entity.Role;
import fr.rbo.elitapi.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @GetMapping(value="/roles")
    public List<Role> listeDesRoles(){
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) throw new NotFoundException("Role non trouvé");
        return roles;
    }

    @GetMapping(value = "/roles/role/{role}")
    public Role recupererUnRoleParRole (@PathVariable("role") String role){
        Role roleTrouve = roleRepository.findByRole(role);
        if (roleTrouve == null) throw new NotFoundException("Role inexistant");
        return roleTrouve;
    }

}
