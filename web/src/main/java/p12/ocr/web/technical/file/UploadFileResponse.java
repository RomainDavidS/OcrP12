package p12.ocr.web.technical.file;


import lombok.*;

/**
 * Mise à disposition des informations du fichier télécharger
 */
@Getter
@Setter
@RequiredArgsConstructor
public @Data class UploadFileResponse {


    private String id;


    private String fileName;

    private byte[] data;


    private String fileType;

    private long size;

    private String fileDownloadUri;




}
