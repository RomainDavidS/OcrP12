package p12.ocr.web.beans.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Getter @Setter @NoArgsConstructor
public class TypeSiteBean {

    @Id
    private Long id;

    @NotEmpty(message = "Le nom du type de site est obligatoire.")
    private String name;
}
