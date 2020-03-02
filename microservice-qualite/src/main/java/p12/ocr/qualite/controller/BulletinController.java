package p12.ocr.qualite.controller;

import p12.ocr.qualite.controller.dto.bulletin.BulletinCreateDto;
import p12.ocr.qualite.controller.dto.bulletin.BulletinUpdateDto;
import p12.ocr.qualite.model.Bulletin;
import p12.ocr.qualite.service.bulletin.IBulletinService;
import p12.ocr.qualite.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bulletin")
public class BulletinController {

    @Autowired
    private IBulletinService bulletinService;

    @GetMapping("/find/{id}")
    public Bulletin findById(@PathVariable Long id){ return bulletinService.findById( id );}

    @GetMapping("/all")
    public List<Bulletin> findAll(){return bulletinService.findAll();}

    @PostMapping("/save")
    public Bulletin save(@DTO(BulletinCreateDto.class) @RequestBody Bulletin bulletin){ return bulletinService.save( bulletin );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(BulletinUpdateDto.class) @RequestBody Bulletin bulletin){ bulletinService.save( bulletin );}
}
