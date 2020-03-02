package p12.ocr.web.technical.api.siret;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Etablissement {
    private String siren;
    private String nic;
    private String siret;
    private String statutDiffusionEtablissement;
    private String dateCreationEtablissement;
    private String trancheEffectifsEtablissement;
    private String anneeEffectifsEtablissement;
    private String activitePrincipaleRegistreMetiersEtablissement;
    private String dateDernierTraitementEtablissement;
    private boolean etablissementSiege;
    private int nombrePeriodesEtablissement;
    private UniteLegale uniteLegale;
    private AdresseEtablissement adresseEtablissement;
    private Adresse2Etablissement adresse2Etablissement;
    private List<PeriodesEtablissement> periodesEtablissement;







}
