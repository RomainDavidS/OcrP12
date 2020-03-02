package p12.ocr.marche.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import p12.ocr.marche.technical.enums.indemnisation.IndemnisationStatus;
import p12.ocr.marche.technical.validation.constraint.MontantComptable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
public @Data class Indemnisation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private IndemnisationStatus status;

    private String pdl;

    private String clientName;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date reclamationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date sendEdpDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date returnEdpDate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date closedDate;

    @MontantComptable
    private BigDecimal amount;

    @Column(columnDefinition = "TEXT")
    private String reclamation;

    @Column(columnDefinition = "TEXT")
    private String entrepriseComment;

    @Column(columnDefinition = "TEXT")
    private String edpComment;

    @Column(columnDefinition = "TEXT")
    private String action;

    @ManyToOne
    @JsonBackReference
    private Marche marche;

}
