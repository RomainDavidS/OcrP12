package p12.ocr.agence.controller;

import p12.ocr.agence.controller.dto.managementvisuel.ManagementVisuelCreateDto;
import p12.ocr.agence.controller.dto.managementvisuel.ManagementVisuelUpdateDto;
import p12.ocr.agence.model.ManagementVisuel;
import p12.ocr.agence.service.managementvisuel.IManagementVisuelService;
import p12.ocr.agence.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managementVisuel")
public class ManagementVisuelController {

    @Autowired
    private IManagementVisuelService managementVisuelService;

    @GetMapping("/find/{id}")
    public ManagementVisuel findById(@PathVariable String id){ return managementVisuelService.findById( id );}

    @GetMapping("/all")
    public List<ManagementVisuel> findAll(){return managementVisuelService.findAll();}

    @PostMapping("/save")
    public ManagementVisuel save(@DTO(ManagementVisuelCreateDto.class) @RequestBody ManagementVisuel managementVisuel){ return managementVisuelService.save( managementVisuel );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(ManagementVisuelUpdateDto.class) @RequestBody ManagementVisuel managementVisuel){ managementVisuelService.save( managementVisuel );}

}
