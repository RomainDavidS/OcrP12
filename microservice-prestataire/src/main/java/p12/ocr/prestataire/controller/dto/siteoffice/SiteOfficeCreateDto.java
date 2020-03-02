package p12.ocr.prestataire.controller.dto.siteoffice;

import p12.ocr.prestataire.model.SitePrestataire;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SiteOfficeCreateDto {

    private String fonction;

    private String rhName;

    private String officePhone;

    private String cellPhone;

    private String email;

    private SitePrestataire sitePrestataire;
}
