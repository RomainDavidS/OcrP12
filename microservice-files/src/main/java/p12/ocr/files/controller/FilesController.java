package p12.ocr.files.controller;


import p12.ocr.files.controller.dto.files.FilesCreateDto;
import p12.ocr.files.controller.dto.files.FilesUpdateDto;
import p12.ocr.files.model.Files;
import p12.ocr.files.service.files.IFilesService;
import p12.ocr.files.technical.dto.DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private IFilesService filesService;

    @GetMapping("/find/{id}")
    public Files findById(@PathVariable String id){ return filesService.findById( id );}

    @GetMapping("/findFileName/{id}")
    public String findFileNameById(@PathVariable String id){ return filesService.findFileNameById( id );}

    @GetMapping("/all")
    public List<Files> findAll(){return filesService.findAll();}

    @PostMapping("/save")
    public Files save(@DTO(FilesCreateDto.class) @RequestBody Files files){ return filesService.save( files );}

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@DTO(FilesUpdateDto.class) @RequestBody Files files){ filesService.save( files );}
}
