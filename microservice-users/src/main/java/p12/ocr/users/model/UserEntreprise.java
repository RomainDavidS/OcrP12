package p12.ocr.users.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity @NoArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"email" , "nni"})})
public @Data class UserEntreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nni;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String cellPhone;

    private String officePhone;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastConnection;

    private boolean enabled;

    private boolean accountNoExpired;

    private boolean accountNoLocked;

    private boolean tokenNoExpired;

    @ManyToOne
    @JoinColumn(name="id_role", referencedColumnName="id")
    private Role role;

    @ManyToOne
    @JoinColumn(name="id_organization", referencedColumnName="id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name="id_site", referencedColumnName="id")
    private Site site;

    @ManyToOne
    @JoinColumn(name="id_fonction", referencedColumnName="id")
    private Fonction fonction;

}
