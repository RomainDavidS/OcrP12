package p12.ocr.prestataire.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
public @Data class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String siret;

    private String adressPostaleComplement;

    private String adressPostaleAdress;

    private String adressPostaleCommune;

    private boolean enabled;

}
