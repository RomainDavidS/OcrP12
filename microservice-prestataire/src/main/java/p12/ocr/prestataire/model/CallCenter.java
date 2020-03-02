package p12.ocr.prestataire.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public @Data class CallCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String phone;

    @Column(columnDefinition = "TEXT")
    private String openingTime;

    private boolean enabled;
}
