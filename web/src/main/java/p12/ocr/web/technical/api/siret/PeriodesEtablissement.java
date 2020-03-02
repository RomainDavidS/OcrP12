package p12.ocr.web.technical.api.siret;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PeriodesEtablissement {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateFin;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;

    private String etatAdministratifEtablissement;
    private boolean changementEtatAdministratifEtablissement;
    private String enseigne1Etablissement;
    private String enseigne2Etablissement;
    private String enseigne3Etablissement;
    private boolean changementEnseigneEtablissement;
    private String denominationUsuelleEtablissement;
    private boolean changementDenominationUsuelleEtablissement;
    private String activitePrincipaleEtablissement;
    private String nomenclatureActivitePrincipaleEtablissement;
    private boolean changementActivitePrincipaleEtablissement;
    private String caractereEmployeurEtablissement;
    private boolean changementCaractereEmployeurEtablissement;
    private Etablissement etablissement;















}
