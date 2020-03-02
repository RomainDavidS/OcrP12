package p12.ocr.web.beans.marche;


import p12.ocr.web.technical.enums.marche.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor
public class TypeMarcheBean {

    @Id
    private Long id;

    @NotEmpty(message = "Le nom du type de marché est obligatoire.")
    private String name;

    @NotNull(message = "Vous devez sélectionner un genre de marché.")
    private Gender gender;
}
