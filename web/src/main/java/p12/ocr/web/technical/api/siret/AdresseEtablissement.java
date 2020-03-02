package p12.ocr.web.technical.api.siret;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdresseEtablissement {
    private String complementAdresseEtablissement;
    private String numeroVoieEtablissement;
    private String indiceRepetitionEtablissement;
    private String typeVoieEtablissement;
    private String libelleVoieEtablissement;
    private String codePostalEtablissement;
    private String libelleCommuneEtablissement;
    private String libelleCommuneEtrangerEtablissement;
    private String distributionSpecialeEtablissement;
    private String codeCommuneEtablissement;
    private String codeCedexEtablissement;
    private String libelleCedexEtablissement;
    private String codePaysEtrangerEtablissement;
    private String libellePaysEtrangerEtablissement;
}
