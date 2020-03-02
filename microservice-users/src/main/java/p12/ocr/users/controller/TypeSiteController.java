package p12.ocr.users.controller;

import p12.ocr.users.controller.dto.typesite.TypeSiteCreateDto;
import p12.ocr.users.controller.dto.typesite.TypeSiteUpdateDto;
import p12.ocr.users.model.TypeSite;
import p12.ocr.users.service.typesite.ITypeSiteService;
import p12.ocr.users.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeSite")
public class TypeSiteController {
    @Autowired
    private ITypeSiteService typeSiteService;

    @GetMapping("/find/{id}")
    public TypeSite findById(@PathVariable Long id){ return typeSiteService.findById( id );}

    @GetMapping("/all")
    public List<TypeSite> findAll(){return typeSiteService.findAll();}

    @PostMapping("/save")
    public TypeSite save(@DTO(TypeSiteCreateDto.class) @RequestBody TypeSite typeSite){ return typeSiteService.save( typeSite );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(TypeSiteUpdateDto.class) @RequestBody TypeSite typeSite){ typeSiteService.save( typeSite );}
}
