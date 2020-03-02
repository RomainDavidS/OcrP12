package p12.ocr.users.controller;

import p12.ocr.users.controller.dto.site.SiteCreateDto;
import p12.ocr.users.controller.dto.site.SiteUpdateDto;
import p12.ocr.users.model.Site;
import p12.ocr.users.service.site.ISiteService;
import p12.ocr.users.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/site")
public class SiteController {

    @Autowired
    private ISiteService siteService;

    @GetMapping("/find/{id}")
    public Site findById(@PathVariable Long id){ return siteService.findById( id );}

    @GetMapping("/all")
    public List<Site> findAll(){return siteService.findAll();}

    @PostMapping("/save")
    public Site save(@DTO(SiteCreateDto.class) @RequestBody Site site){ return siteService.save( site );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(SiteUpdateDto.class) @RequestBody Site site){ siteService.save( site );}
}
