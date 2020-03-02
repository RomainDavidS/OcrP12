package p12.ocr.marche.controller;

import p12.ocr.marche.controller.dto.marche.MarcheCreateDto;
import p12.ocr.marche.controller.dto.marche.MarcheUpdateDto;
import p12.ocr.marche.model.Marche;
import p12.ocr.marche.service.marche.IMarcheService;
import p12.ocr.marche.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marche")
public class MarcheController {

    @Autowired
    private IMarcheService marcheService;

    @GetMapping("/find/{id}")
    public Marche findById(@PathVariable Long id){ return marcheService.findById( id );}

    @GetMapping("/all")
    public List<Marche> findAll(){return marcheService.findAll();}

    @PostMapping("/save")
    public Marche save(@DTO(MarcheCreateDto.class) @RequestBody Marche marche){ return marcheService.save( marche );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(MarcheUpdateDto.class) @RequestBody Marche marche){ marcheService.save( marche );}

}
