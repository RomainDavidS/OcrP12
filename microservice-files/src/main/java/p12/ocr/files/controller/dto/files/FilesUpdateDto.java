package p12.ocr.files.controller.dto.files;


import p12.ocr.files.technical.enums.typefile.TypeFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class FilesUpdateDto {

    @Id
    private String id;

    private String fileName;

    private String fileType;

    private byte[] data;

    private Long size;

    private TypeFile typeFile;
}
