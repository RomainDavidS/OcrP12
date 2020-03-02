package p12.ocr.prestataire.controller.dto.siteprestataire;

import p12.ocr.prestataire.model.CallCenter;
import p12.ocr.prestataire.model.Employee;
import p12.ocr.prestataire.model.Entreprise;
import p12.ocr.prestataire.model.SiteOffice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SitePrestataireUpdateDto {

    @Id
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

    private CallCenter callCenter;

    private Entreprise entreprise;

    private List<Employee> employeeList;

    private List<SiteOffice> siteOfficeList;


}
