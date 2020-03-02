package p12.ocr.users.controller.dto.userentreprise;

import p12.ocr.users.model.Fonction;
import p12.ocr.users.model.Organization;
import p12.ocr.users.model.Role;
import p12.ocr.users.model.Site;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class UserEntrepriseUpdateDto {

    @Id
    private Long id;

    private String nni;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String cellPhone;

    private String officePhone;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCreate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastConnection;

    private boolean enabled;

    private boolean accountNoExpired;

    private boolean accountNoLocked;

    private boolean tokenNoExpired;

    private Role role;

    private Organization organization;

    private Site site;

    private Fonction fonction;
}
