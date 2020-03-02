package p12.ocr.prestataire.controller.dto.fonctionprestataire;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class FonctionPrestataireUpdateDto {

    @Id
    private Long id;

    private String name;
}
