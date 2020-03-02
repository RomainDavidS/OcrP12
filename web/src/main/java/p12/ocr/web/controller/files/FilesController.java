package p12.ocr.web.controller.files;

import p12.ocr.web.beans.files.FilesBean;
import p12.ocr.web.service.files.files.IFilesService;
import p12.ocr.web.technical.enums.typefile.TypeFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FilesController {

    private static final Logger logger = LoggerFactory.getLogger(FilesController.class);

    @Autowired
    private IFilesService filesService;

    @PostMapping("/uploadFile")
    public FilesBean uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("typeFile") TypeFile typeFile)  {
        return filesService.storeFile( file, typeFile );
    }

    @PostMapping("/uploadMultipleFiles")
    public List<FilesBean> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("typeFile") TypeFile typeFile) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, typeFile ))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) {

        FilesBean filesBean = filesService.findById( fileId );

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(filesBean.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filesBean.getFileName() + "\"")
                .body(new ByteArrayResource(filesBean.getData() ) );
    }
}
