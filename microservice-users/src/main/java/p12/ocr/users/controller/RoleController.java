package p12.ocr.users.controller;

import p12.ocr.users.model.Role;
import p12.ocr.users.service.role.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/find/{id}")
    public Role findById(@PathVariable Long id){ return roleService.findById( id );}

    @GetMapping("/all")
    public List<Role> findAll(){return roleService.findAll();}
}
