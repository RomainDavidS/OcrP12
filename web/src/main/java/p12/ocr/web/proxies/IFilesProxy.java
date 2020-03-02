package p12.ocr.web.proxies;

import p12.ocr.web.beans.files.FilesBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "zuul-server",contextId = "filesProxy")
@RibbonClient(name = "microservice-files")
@RequestMapping("/microservice-files")
public interface IFilesProxy {

    /* Files */
    @GetMapping("/files/find/{id}")
    FilesBean findFilesById(@PathVariable String id);

    @GetMapping("/files/all")
    List<FilesBean> findAllFiles();

    @GetMapping("/files/findFileName/{id}")
    String findFileNameById(@PathVariable String id);

    @PostMapping("/files/save")
    FilesBean saveFiles(@RequestBody FilesBean files);

    @PutMapping("/files/update")
    void updateFiles(@RequestBody FilesBean files);



}
