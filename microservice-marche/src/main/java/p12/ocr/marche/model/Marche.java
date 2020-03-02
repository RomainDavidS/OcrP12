package p12.ocr.marche.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public @Data class Marche {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String numero;

    private String eTravaux;

    private Long fournisseur;

    private Long client;

    private Long idPrestataire;

    private Long idSousTraitant;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateEnd;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateClosed;

    private Long idSite;

    private Long idRef1;

    private Long idRef2;

    private Long idOrganization;

    @ManyToOne
    @JoinColumn(name="type_marche_id", referencedColumnName="id")
    private TypeMarche typeMarche;

    @OneToMany(mappedBy = "marche")
    @JsonManagedReference
    private List<Indemnisation> indemnisationList;




}
