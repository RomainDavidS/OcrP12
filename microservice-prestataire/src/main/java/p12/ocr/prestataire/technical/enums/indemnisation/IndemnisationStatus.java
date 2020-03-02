package p12.ocr.prestataire.technical.enums.indemnisation;

import lombok.Getter;

@Getter
public enum IndemnisationStatus {
    IN_PROGRESS("En cours"),
    FINISHED("Terminé");

    private String code;

    IndemnisationStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
