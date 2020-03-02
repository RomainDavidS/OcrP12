package p12.ocr.web.beans.users;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;


@Getter @Setter @NoArgsConstructor
public class RoleBean {

    @Id
    private Long id;

    private String name;

    private String libelle;

    private List<PrivilegeBean> privilegeList;
}
