package p12.ocr.marche.model;

import p12.ocr.marche.technical.enums.marche.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public @Data class TypeMarche {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Gender gender;


}
