package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.controller.dto.siteprestataire.SitePrestataireCreateDto;
import p12.ocr.prestataire.controller.dto.siteprestataire.SitePrestataireUpdateDto;
import p12.ocr.prestataire.model.SitePrestataire;
import p12.ocr.prestataire.service.siteprestataire.ISitePrestataireService;
import p12.ocr.prestataire.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sitePrestataire")
public class SitePrestataireController {

    @Autowired
    private ISitePrestataireService siteService;

    @GetMapping("/find/{id}")
    public SitePrestataire findById(@PathVariable Long id){ return siteService.findById( id );}

    @GetMapping("/all")
    public List<SitePrestataire> findAll(){return siteService.findAll();}

    @PostMapping("/save")
    public SitePrestataire save(@DTO(SitePrestataireCreateDto.class) @RequestBody SitePrestataire sitePrestataire){ return siteService.save(sitePrestataire);}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(SitePrestataireUpdateDto.class) @RequestBody SitePrestataire sitePrestataire){ siteService.save(sitePrestataire);}

}
