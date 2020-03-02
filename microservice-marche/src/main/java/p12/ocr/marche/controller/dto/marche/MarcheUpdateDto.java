package p12.ocr.marche.controller.dto.marche;

import p12.ocr.marche.model.Indemnisation;
import p12.ocr.marche.model.TypeMarche;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class MarcheUpdateDto {

    @Id
    private Long id;

    private String name;

    private String numero;

    private String eTravaux;

    private Long fournisseur;

    private Long client;

    private Long idPrestataire;

    private Long idSousTraitant;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateStart;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateEnd;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dateClosed;

    private Long idSite;

    private Long idRef1;

    private Long idRef2;

    private Long idOrganization;

    private TypeMarche typeMarche;

    private List<Indemnisation> indemnisationList;



}
