package p12.ocr.web.service.files.files;

import p12.ocr.web.beans.files.FilesBean;
import p12.ocr.web.technical.enums.typefile.TypeFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFilesService {

    FilesBean storeFile(MultipartFile file, TypeFile typeFile);

    FilesBean findById(String id );
    String findFileNameById(String id );
    List<FilesBean> findAll();
    FilesBean save(FilesBean filesBean );
    void update(FilesBean filesBean );
}
