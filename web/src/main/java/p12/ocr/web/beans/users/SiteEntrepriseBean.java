package p12.ocr.web.beans.users;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter @NoArgsConstructor
public class SiteEntrepriseBean {

    @Id
    private Long id;

    @NotEmpty(message = "Le nom du site est obligatoire." )
    private String name;

    @NotNull(message = "Le type de site est obligatoire.")
    private TypeSiteBean typeSite;
}
