package p12.ocr.users.controller;

import p12.ocr.users.model.Privilege;
import p12.ocr.users.service.privilege.IPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

    @Autowired
    private IPrivilegeService privilegeService;

    @GetMapping("/find/{name}")
    public Privilege findByName(@PathVariable String name){ return privilegeService.findByName( name );}

    @GetMapping("/all")
    public List<Privilege> findAll(){return privilegeService.findAll();}
}
