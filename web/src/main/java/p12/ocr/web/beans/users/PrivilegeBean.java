package p12.ocr.web.beans.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter @Setter @NoArgsConstructor
public class PrivilegeBean {

    @Id
    private Long id;

    @NotEmpty(message = "Le nom du privilège est obligatoire.")
    private String name;

    @NotEmpty(message = "Le libellé du privilège est obligatoire.")
    private String libelle;

}
