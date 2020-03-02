package p12.ocr.web.beans.agence;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter @Setter @NoArgsConstructor
public class ManagementVisuelBean {

    @Id
    @NotBlank(message = "Le visuel est obligatoire.")
    private String id;

    @NotBlank(message = "Le nom du management visuel est obligatoire.")
    private String name;

    private boolean publication;
}
