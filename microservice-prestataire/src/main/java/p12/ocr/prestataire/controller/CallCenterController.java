package p12.ocr.prestataire.controller;

import p12.ocr.prestataire.controller.dto.callcenter.CallCenterCreateDto;
import p12.ocr.prestataire.controller.dto.callcenter.CallCenterUpdateDto;
import p12.ocr.prestataire.model.CallCenter;
import p12.ocr.prestataire.service.callcenter.ICallCenterService;
import p12.ocr.prestataire.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/callCenter")
public class CallCenterController {

    @Autowired
    private ICallCenterService callCenterService;

    @GetMapping("/find/{id}")
    public CallCenter findById(@PathVariable Long id){ return callCenterService.findById( id );}

    @GetMapping("/all")
    public List<CallCenter> findAll(){return callCenterService.findAll();}

    @PostMapping("/save")
    public CallCenter save(@DTO(CallCenterCreateDto.class) @RequestBody CallCenter callCenter){ return callCenterService.save( callCenter );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(CallCenterUpdateDto.class) @RequestBody CallCenter callCenter){ callCenterService.save( callCenter );}


}
