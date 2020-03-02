package p12.ocr.prestataire.controller.dto.entreprise;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EntrepriseCreateDto {


    private String name;

    private String siret;

    private String adressPostaleComplement;

    private String adressPostaleAdress;

    private String adressPostaleCommune;

    private boolean enabled;

}
