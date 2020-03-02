package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.controller.dto.siteoffice.SiteOfficeCreateDto;
import p12.ocr.prestataire.controller.dto.siteoffice.SiteOfficeUpdateDto;
import p12.ocr.prestataire.model.SiteOffice;
import p12.ocr.prestataire.service.siteoffice.ISiteOfficeService;
import p12.ocr.prestataire.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/siteOffice")
public class SiteOfficeController {

    @Autowired
    private ISiteOfficeService siteOfficeService;

    @GetMapping("/find/{id}")
    public SiteOffice findById(@PathVariable Long id){ return siteOfficeService.findById( id );}

    @GetMapping("/all")
    public List<SiteOffice> findAll(){return siteOfficeService.findAll();}

    @PostMapping("/save")
    public SiteOffice save(@DTO(SiteOfficeCreateDto.class) @RequestBody SiteOffice siteOffice){ return siteOfficeService.save( siteOffice );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(SiteOfficeUpdateDto.class) @RequestBody SiteOffice siteOffice){ siteOfficeService.save( siteOffice );}

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id){ siteOfficeService.delete( id );}
}
