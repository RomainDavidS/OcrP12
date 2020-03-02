package p12.ocr.agence.technical.enums.typefile;

public enum TypeFile {
    QUALITE("Qualité"),
    CONTRACT_DOCUMENT("Document Prestataire"),
    IDENTITY_PHOTO("Photo identité"),
    EPDR_MV("Management Visuel");


    private String code;

    TypeFile(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
