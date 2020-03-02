package p12.ocr.qualite.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import p12.ocr.qualite.technical.enums.typebulletin.TypeBulletin;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public @Data class Bulletin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String idBulletin;

    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private TypeBulletin typeBulletin;

    @OneToMany(mappedBy = "bulletin")
    @JsonManagedReference
    private List<Portage> portageList;
}
