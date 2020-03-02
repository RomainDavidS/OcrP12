package p12.ocr.qualite.controller;


import p12.ocr.qualite.controller.dto.marchequalite.MarcheQualiteDto;
import p12.ocr.qualite.model.MarcheQualite;
import p12.ocr.qualite.service.marchequalite.IMarcheQualiteService;
import p12.ocr.qualite.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marche")
public class MarcheQualiteController {

    @Autowired
    private IMarcheQualiteService marcheService;

    @GetMapping("/find/{id}")
    public MarcheQualite findById(@PathVariable Long id){ return marcheService.findById( id );}

    @GetMapping("/all")
    public List<MarcheQualite> findAll(){return marcheService.findAll();}

    @PostMapping("/save")
    public MarcheQualite save(@DTO(MarcheQualiteDto.class) @RequestBody MarcheQualite marcheQualite){ return marcheService.save(marcheQualite);}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(MarcheQualiteDto.class) @RequestBody MarcheQualite marcheQualite){ marcheService.save(marcheQualite);}
}
