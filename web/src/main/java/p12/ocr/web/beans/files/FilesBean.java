package p12.ocr.web.beans.files;

import p12.ocr.web.technical.enums.typefile.TypeFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class FilesBean {

    @Id
    private String id;

    private String fileName;

    private String fileType;

    private byte[] data;

    private Long size;

    private TypeFile typeFile;
}
