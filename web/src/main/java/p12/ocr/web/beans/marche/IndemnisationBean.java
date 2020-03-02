package p12.ocr.web.beans.marche;


import p12.ocr.web.technical.enums.indemnisation.IndemnisationStatus;
import p12.ocr.web.technical.validation.constraint.MontantComptable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


@NoArgsConstructor @Getter @Setter
public class IndemnisationBean {

    @Id
    private Long id;

    @NotNull(message = "Le status de la demande est obligatoire")
    private IndemnisationStatus status;

    @NotBlank(message = "Le PDL est obligatoire")
    private String pdl;

    @NotBlank(message = "Le nom du client est obligatoire")
    private String clientName;

    @NotNull(message = "La date de réclamation est obligatoire")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date reclamationDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date sendEdpDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date returnEdpDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date closedDate;

    @MontantComptable
    private BigDecimal amount;

    @NotBlank(message = "Le contenu de la réclamation est obligatoire.")
    private String reclamation;

    private String entrepriseComment;

    private String edpComment;

    private String action;

    private MarcheBean marche;
}
