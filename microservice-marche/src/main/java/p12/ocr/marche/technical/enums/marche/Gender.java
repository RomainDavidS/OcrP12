package p12.ocr.marche.technical.enums.marche;

import lombok.Getter;

@Getter
public enum Gender {
    POSE_C("Pose C"),
    SATURATION("Saturation"),
    POSE_K("Pose K"),
    VQD("Visite Qualité"),
    RECYCLAGE("Recyclage");

    private String code;

    Gender(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
