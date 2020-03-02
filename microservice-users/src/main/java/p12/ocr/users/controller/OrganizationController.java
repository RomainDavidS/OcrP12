package p12.ocr.users.controller;

import p12.ocr.users.controller.dto.organization.OrganizationCreateDto;
import p12.ocr.users.controller.dto.organization.OrganizationUpdateDto;
import p12.ocr.users.model.Organization;
import p12.ocr.users.service.organization.IOrganizationService;
import p12.ocr.users.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;

    @GetMapping("/find/{id}")
    public Organization findById(@PathVariable Long id){ return organizationService.findById( id );}

    @GetMapping("/all")
    public List<Organization> findAll(){return organizationService.findAll();}

    @PostMapping("/save")
    public Organization save(@DTO(OrganizationCreateDto.class) @RequestBody Organization organization){ return organizationService.save( organization );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(OrganizationUpdateDto.class) @RequestBody Organization organization){ organizationService.save( organization );}
}
