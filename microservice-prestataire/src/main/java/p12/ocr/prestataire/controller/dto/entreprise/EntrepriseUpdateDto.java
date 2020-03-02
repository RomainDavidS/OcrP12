package p12.ocr.prestataire.controller.dto.entreprise;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class EntrepriseUpdateDto {

    @Id
    private Long id;

    private String name;

    private String siret;

    private String adressPostaleComplement;

    private String adressPostaleAdress;

    private String adressPostaleCommune;

    private boolean enabled;

}
