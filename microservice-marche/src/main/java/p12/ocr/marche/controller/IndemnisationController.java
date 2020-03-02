package p12.ocr.marche.controller;

import p12.ocr.marche.controller.dto.indemnisation.IndemnisationCreateDto;
import p12.ocr.marche.controller.dto.indemnisation.IndemnisationUpdateDto;
import p12.ocr.marche.model.Indemnisation;
import p12.ocr.marche.service.indemnisation.IIndemnisationService;
import p12.ocr.marche.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/indemnisation")

public class IndemnisationController {

    @Autowired
    private IIndemnisationService indemnisationService;

    @GetMapping("/find/{id}")
    public Indemnisation findById(@PathVariable Long id){ return indemnisationService.findById( id );}

    @GetMapping("/all")
    public List<Indemnisation> findAll(){return indemnisationService.findAll();}

    @PostMapping("/save")
    public Indemnisation save(@DTO(IndemnisationCreateDto.class) @RequestBody Indemnisation indemnisation){ return indemnisationService.save( indemnisation );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(IndemnisationUpdateDto.class) @RequestBody Indemnisation indemnisation){ indemnisationService.save( indemnisation );}

}
