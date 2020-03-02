package p12.ocr.web.beans.prestataire;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class FonctionPrestataireBean {
    @Id
    private Long id;

    private String name;
}
