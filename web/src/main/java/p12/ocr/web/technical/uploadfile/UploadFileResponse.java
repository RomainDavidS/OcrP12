package p12.ocr.web.technical.uploadfile;


import lombok.*;

/**
 * Mise à disposition des informations du fichier télécharger
 */
@Getter
@Setter
@RequiredArgsConstructor
public @Data
class UploadFileResponse {

    @NonNull
    private String id;

    @NonNull
    private String fileName;

    @NonNull
    private String fileDownloadUri;

    @NonNull
    private String fileType;

    @NonNull
    private long size;




}
