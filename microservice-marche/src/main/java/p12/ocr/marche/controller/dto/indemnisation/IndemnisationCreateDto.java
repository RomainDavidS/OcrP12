package p12.ocr.marche.controller.dto.indemnisation;

import p12.ocr.marche.model.Marche;
import p12.ocr.marche.technical.enums.indemnisation.IndemnisationStatus;
import p12.ocr.marche.technical.validation.constraint.MontantComptable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


@NoArgsConstructor @Getter @Setter
public class IndemnisationCreateDto {

    private IndemnisationStatus status;

    private String pdl;

    private String clientName;

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

    private String reclamation;

    private String entrepriseComment;

    private String edpComment;

    private String action;

    private Marche marche;
}
