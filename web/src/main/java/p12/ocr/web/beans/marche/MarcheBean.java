package p12.ocr.web.beans.marche;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class MarcheBean {

    @Id
    private Long id;

    @NotEmpty(message = "Le nom du marché est obligatoire")
    private String name;

    @NotEmpty(message = "Le numéro du marché est obligatoire")
    private String numero;

    @NotEmpty(message = "Le numéro de eTravaux est obligatoire est obligatoire")
    private String eTravaux;

    @NotNull(message = "Le numéro fournisseur est obligatoire")
    private Long fournisseur;

    @NotNull(message = "Le numéro client est obligatoire")
    private Long client;


    private Long idPrestataire;

    private Long idSousTraitant;

    @NotNull(message = "La date de début du marché est obligatoire")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateStart;

    @NotNull(message = "La date de fin du marché est obligatoire")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateEnd;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateClosed;

    private Long idSite;


    private Long idRef1;

    private Long idRef2;

    private Long idOrganization;

    private TypeMarcheBean typeMarche;

    private List<IndemnisationBean> indemnisationList;


}
