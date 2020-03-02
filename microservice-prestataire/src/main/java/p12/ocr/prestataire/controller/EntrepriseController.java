package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.controller.dto.employee.EmployeeCreateDto;
import p12.ocr.prestataire.controller.dto.employee.EmployeeUpdateDto;
import p12.ocr.prestataire.model.Entreprise;
import p12.ocr.prestataire.service.entreprise.IEntrepriseService;
import p12.ocr.prestataire.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entreprise")
public class EntrepriseController {

    @Autowired
    private IEntrepriseService entrepriseService;

    @GetMapping("/find/{id}")
    public Entreprise findById(@PathVariable Long id){ return entrepriseService.findById( id );}

    @GetMapping("/find/siret/{siret}")
    public Entreprise findBySiret(@PathVariable String siret){ return  entrepriseService.findBySiret( siret );}


    @GetMapping("/all")
    public List<Entreprise> findAll(){return entrepriseService.findAll();}

    @PostMapping("/save")
    public Entreprise save(@DTO(EmployeeCreateDto.class) @RequestBody Entreprise entreprise){ return entrepriseService.save( entreprise );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(EmployeeUpdateDto.class) @RequestBody Entreprise entreprise){ entrepriseService.save( entreprise );}

}
