package p12.ocr.users.controller.dto.role;

import p12.ocr.users.model.Privilege;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter @Setter @NoArgsConstructor
public class RoleDto {

    @Id
    private Long id;

    private String name;

    private String libelle;

    private List<Privilege> privilegeList;
}
