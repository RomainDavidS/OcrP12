package p12.ocr.prestataire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public @Data class SiteOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fonction;

    private String rhName;

    private String officePhone;

    private String cellPhone;

    private String email;

    @ManyToOne
    @JsonBackReference
    private SitePrestataire sitePrestataire;



}
