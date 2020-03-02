package p12.ocr.web.beans.prestataire;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class SiteOfficeBean {

    @Id
    private Long id;

    @NotBlank(message = "La fonction est obligatoire.")
    private String fonction;

    @NotBlank(message = "Le nom est obligatoire.")
    private String rhName;

    @NotBlank(message = "Le numéro de téléphone du bureau est obligatoire.")
    private String officePhone;

    private String cellPhone;

    @NotBlank(message = "L''adresse mail est obligatoire est obligatoire.")
    private String email;

    private SitePrestataireBean sitePrestataire;
}
