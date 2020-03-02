package p12.ocr.marche.controller.dto.typemarche;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TypeMarcheCreateDto {

    private String name;
    private boolean enabled;
}
