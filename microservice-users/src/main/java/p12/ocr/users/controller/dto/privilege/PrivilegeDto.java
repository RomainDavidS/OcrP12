package p12.ocr.users.controller.dto.privilege;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
public class PrivilegeDto {

    @Id
    private Long id;

    private String name;

    private String libelle;

}
