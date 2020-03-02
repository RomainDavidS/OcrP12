package p12.ocr.users.controller;



import p12.ocr.users.controller.dto.userentreprise.UserEntrepriseCreateDto;
import p12.ocr.users.controller.dto.userentreprise.UserEntrepriseUpdateDto;
import p12.ocr.users.model.UserEntreprise;
import p12.ocr.users.service.userentreprise.IUserEntrepriseService;
import p12.ocr.users.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userEntreprise")
public class UserEntrepriseController {

    @Autowired
    private IUserEntrepriseService userEntrepriseService;

    @GetMapping("/id/{id}")
    public UserEntreprise findById(@PathVariable Long id){ return userEntrepriseService.findById( id );}

    @GetMapping("/nni/{nni}")
    public UserEntreprise findByNni(@PathVariable String nni){ return userEntrepriseService.findByNni( nni );}

    @GetMapping("/all")
    public List<UserEntreprise> findAll(){return userEntrepriseService.findAll();}

    @PostMapping("/save")
    public UserEntreprise save(@DTO(UserEntrepriseCreateDto.class) @RequestBody UserEntreprise userEntreprise){ return userEntrepriseService.save(userEntreprise);}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(UserEntrepriseUpdateDto.class) @RequestBody UserEntreprise userEntreprise){ userEntrepriseService.save(userEntreprise);}





}
