package p12.ocr.web.beans.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;


@Getter @Setter @NoArgsConstructor
public class FonctionBean {

    @Id
    private Long id;

    @NotEmpty(message = "Le nom de la fonction est obligatoire.")
    private String name;

}
