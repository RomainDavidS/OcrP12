package p12.ocr.prestataire.controller.dto.siteoffice;

import p12.ocr.prestataire.model.SitePrestataire;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class SiteOfficeUpdateDto {

    @Id
    private Long id;

    private String fonction;

    private String rhName;

    private String officePhone;

    private String cellPhone;

    private String email;

    private SitePrestataire sitePrestataire;
}


