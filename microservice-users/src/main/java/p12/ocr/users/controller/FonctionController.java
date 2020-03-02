package p12.ocr.users.controller;


import p12.ocr.users.controller.dto.fonction.FonctionCreateDto;
import p12.ocr.users.controller.dto.fonction.FonctionUpdateDto;
import p12.ocr.users.model.Fonction;
import p12.ocr.users.service.fonction.IFonctionService;
import p12.ocr.users.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fonction")
public class FonctionController {

    @Autowired
    private IFonctionService fonctionService;

    @GetMapping("/find/{id}")
    public Fonction findById(@PathVariable Long id){ return fonctionService.findById( id );}

    @GetMapping("/all")
    public List<Fonction> findAll(){return fonctionService.findAll();}

    @PostMapping("/save")
    public Fonction save(@DTO(FonctionCreateDto.class) @RequestBody Fonction fonction){ return fonctionService.save( fonction );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(FonctionUpdateDto.class) @RequestBody Fonction fonction){ fonctionService.save( fonction );}


}
