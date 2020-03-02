package p12.ocr.marche.controller;

import p12.ocr.marche.controller.dto.typemarche.TypeMarcheCreateDto;
import p12.ocr.marche.controller.dto.typemarche.TypeMarcheUpdateDto;
import p12.ocr.marche.model.TypeMarche;
import p12.ocr.marche.service.typemarche.ITypeMarcheService;
import p12.ocr.marche.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typeMarche")
public class TypeMarcheController {

    @Autowired
    private ITypeMarcheService typeMarcheService;

    @GetMapping("find/{id}")
    public TypeMarche findById(@PathVariable Long id){ return typeMarcheService.findById( id );}

    @GetMapping("/all")
    public List<TypeMarche> findAll(){return typeMarcheService.findAll();}

    @PostMapping("/save")
    public TypeMarche save(@DTO(TypeMarcheCreateDto.class) @RequestBody TypeMarche typeMarche){ return typeMarcheService.save( typeMarche );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(TypeMarcheUpdateDto.class) @RequestBody TypeMarche typeMarche){ typeMarcheService.save( typeMarche );}

}
