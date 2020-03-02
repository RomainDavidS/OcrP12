package p12.ocr.web.beans.prestataire;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeBean {
    @Id
    private Long id;

    @NotBlank(message = "Le nni est obligatoire")
    private String nni;

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;

    private String email;

    private String cellPhone;

    private String officePhone;

    private boolean enabled;

    private boolean newEmployee;

    private String idContract;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "La date de début du contrat est obligatoire.")
    private Date startDateContract;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDateContract;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date signatureDateContract;

    private String idConfidential;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "La date de début de l''engagement est obligatoire.")
    private Date startDateConfidential;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDateConfidential;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date signatureDateConfidential;

    private String idHabilitation;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "La date de début du titre d''habilitation est obligatoire.")
    private Date startDateHabilitation;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDateHabilitation;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date signatureDateHabilitation;

    private String idQualification;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "La date de début du titre de qualification est obligatoire.")
    private Date startDateQualification;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDateQualification;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date signatureDateQualification;

    private String idIpsItst;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "La date de début de l''IPS ITST est obligatoire.")
    private Date startDateIpsItst;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDateIpsItst;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date signatureDateIpsItst;

    private EntrepriseBean entreprise;

    private String idIdentityPhoto;

    private FonctionPrestataireBean fonctionPrestataire;

    private SitePrestataireBean sitePrestataire;

}
