package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.controller.dto.fonctionprestataire.FonctionPrestataireCreateDto;
import p12.ocr.prestataire.controller.dto.fonctionprestataire.FonctionPrestataireUpdateDto;
import p12.ocr.prestataire.model.FonctionPrestataire;
import p12.ocr.prestataire.service.fonctionprestataire.IFonctionPrestataireService;
import p12.ocr.prestataire.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fonctionPrestataire")
public class FonctionPrestataireController {

    @Autowired
    private IFonctionPrestataireService fonctionPrestataireService;

    @GetMapping("/find/{id}")
    public FonctionPrestataire findById(@PathVariable Long id){ return fonctionPrestataireService.findById( id );}

    @GetMapping("/all")
    public List<FonctionPrestataire> findAll(){return fonctionPrestataireService.findAll();}

    @PostMapping("/save")
    public FonctionPrestataire save(@DTO(FonctionPrestataireCreateDto.class) @RequestBody FonctionPrestataire fonctionPrestataire){ return fonctionPrestataireService.save( fonctionPrestataire );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(FonctionPrestataireUpdateDto.class) @RequestBody FonctionPrestataire fonctionPrestataire){ fonctionPrestataireService.save( fonctionPrestataire );}

}
