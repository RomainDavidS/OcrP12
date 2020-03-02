package p12.ocr.marche.controller.dto.typemarche;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter @Setter @NoArgsConstructor
public class TypeMarcheUpdateDto {

    @Id
    private Long id;

    private String name;

    private boolean enabled;
}
