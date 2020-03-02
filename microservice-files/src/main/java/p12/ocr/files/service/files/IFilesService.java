package p12.ocr.files.service.files;

import p12.ocr.files.model.Files;

import java.util.List;

public interface IFilesService {

    Files findById(String id);
    String findFileNameById(String id);
    List<Files> findAll();
    Files save(Files files);

}
