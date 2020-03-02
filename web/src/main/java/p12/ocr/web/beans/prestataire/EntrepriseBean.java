package p12.ocr.web.beans.prestataire;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EntrepriseBean {

    @Id
    private Long id;

    @NotBlank(message = "Le nom de l''entreprise est obligatoire.")
    private String name;

    @NotBlank(message = "Le numéro Siret est obligatoire.")
    private String siret;

    private String adressPostaleComplement;

    @NotBlank(message = "L''adresse de l''entreprise est obligatoire.")
    private String adressPostaleAdress;

    @NotBlank(message = "La commune de l''entreprise est obligatoire.")
    private String adressPostaleCommune;

    private boolean enabled;

}
