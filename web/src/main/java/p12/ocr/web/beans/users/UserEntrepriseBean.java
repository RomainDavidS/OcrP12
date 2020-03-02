package p12.ocr.web.beans.users;

import p12.ocr.web.technical.fieldmatch.FieldNoMatch;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@FieldNoMatch.List({
        @FieldNoMatch(first = "cellPhone", second = "officePhone", message = "Les numéros de téléphone doivent être différents.")
})
@NoArgsConstructor
public @Data class UserEntrepriseBean {

    @Id
    private Long id;

    @NotEmpty(message = "Le NNI est obligatoire.")
    private String nni;

    @NotEmpty(message = "Le prénom est obligatoire.")
    private String firstName;

    @NotEmpty(message = "Le nom est obligatoire.")
    private String lastName;

    @Email
    @NotEmpty(message = "L''adresse mail obligatoire.")
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

    @NotNull
    private RoleBean role;

    @NotNull
    private OrganizationBean organization;

    @NotNull
    private SiteEntrepriseBean site;

    @NotNull
    private FonctionBean fonction;

}
