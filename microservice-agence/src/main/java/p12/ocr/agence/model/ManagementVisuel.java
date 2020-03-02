package p12.ocr.agence.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@NoArgsConstructor
public @Data class ManagementVisuel {

    @Id
    private String id;

    private String name;

    private boolean publication;
}
