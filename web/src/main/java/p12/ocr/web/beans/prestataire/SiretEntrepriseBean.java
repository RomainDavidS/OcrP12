package p12.ocr.web.beans.prestataire;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SiretEntrepriseBean {

    private int responseCode;

    private String responseLibelle;

    private String name;

    private String siret;

    private String adressPostaleComplement;

    private String adressPostaleAdress;

    private String adressPostaleCommune;
}
