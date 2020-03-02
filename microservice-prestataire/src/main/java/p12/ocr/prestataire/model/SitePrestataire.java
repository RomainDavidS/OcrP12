package p12.ocr.prestataire.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public @Data class SitePrestataire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Long codeZs;

    private String adressLocalComplement;

    private String adressLocalAdress;

    private String adressLocalCommune;

    private String adressPostaleComplement;

    private String adressPostaleAdress;

    private String adressPostaleCommune;

    private boolean enabled;

    @ManyToOne
    private CallCenter callCenter;

    @ManyToOne
    private Entreprise entreprise;

    @OneToMany(mappedBy = "sitePrestataire")
    @JsonManagedReference
    private List<Employee> employeeList;

    @OneToMany(mappedBy = "sitePrestataire")
    @JsonManagedReference
    private List<SiteOffice> siteOfficeList;




}
