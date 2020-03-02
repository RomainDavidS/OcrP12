package p12.ocr.qualite.controller;


import p12.ocr.qualite.controller.dto.portage.PortageCreateDto;
import p12.ocr.qualite.controller.dto.portage.PortageUpdateDto;
import p12.ocr.qualite.model.Portage;
import p12.ocr.qualite.service.portage.IPortageService;
import p12.ocr.qualite.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portage")
public class PortageController {

    @Autowired
    private IPortageService portageService;

    @GetMapping("/find/{id}")
    public Portage findById(@PathVariable Long id){ return portageService.findById( id );}

    @GetMapping("/all")
    public List<Portage> findAll(){return portageService.findAll();}

    @PostMapping("/save")
    public Portage save(@DTO(PortageCreateDto.class) @RequestBody Portage portage){ return portageService.save( portage );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(PortageUpdateDto.class) @RequestBody Portage portage){ portageService.save( portage );}
}
