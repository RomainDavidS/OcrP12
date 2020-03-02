package p12.ocr.web.beans.prestataire;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SitePrestataireBean {

    @Id
    private Long id;

    @NotBlank(message = "Le nom du site est obligatoire.")
    private String name;

    @NotNull(message = "Le code ZS du site est obligatoire.")
    private Long codeZs;

    private String adressLocalComplement;

    @NotBlank(message = "L''adresse du site est obligatoire.")
    private String adressLocalAdress;

    @NotBlank(message = "Le nom de la commune du site est obligatoire.")
    private String adressLocalCommune;

    private String adressPostaleComplement;

    @NotBlank(message = "L''adresse postale du site est obligatoire.")
    private String adressPostaleAdress;

    @NotBlank(message = "Le nom de la commune de l''adresse postale du site est obligatoire.")
    private String adressPostaleCommune;

    private boolean enabled;

    private CallCenterBean callCenter;

    private EntrepriseBean entreprise;

    private List<EmployeeBean> employeeList;

    private List<SiteOfficeBean> siteOfficeList;


}
