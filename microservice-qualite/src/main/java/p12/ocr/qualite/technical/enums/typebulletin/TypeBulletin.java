package p12.ocr.qualite.technical.enums.typebulletin;

import lombok.Getter;

@Getter
public enum TypeBulletin {

    BULLETIN_QUALITE("Bulletin Qualité"),
    INFO_TECH("Infos technique"),
    INFO_DIVERS("Infos Divers"),
    ACCIDENT("Fiches accidents"),
    PREVENTION("Actions préventions"),
    ACTION_DIVERS("Actions divers");



    private String code;

    TypeBulletin(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
