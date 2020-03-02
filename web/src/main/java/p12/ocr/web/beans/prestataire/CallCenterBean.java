package p12.ocr.web.beans.prestataire;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter @Setter @NoArgsConstructor
public class CallCenterBean {

    @Id
    private Long id;

    @NotBlank(message = "Le nom du call center est obligatoire.")
    private String name;

    @NotBlank(message = "Le téléphone du call center est obligatoire.")
    private String phone;

    @NotBlank(message = "Les heures d''ouverture du call center est obligatoire.")
    private String openingTime;

    private boolean enabled;
}
