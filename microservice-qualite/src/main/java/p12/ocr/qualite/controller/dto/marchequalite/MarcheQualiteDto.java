package p12.ocr.qualite.controller.dto.marchequalite;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter @Setter @NoArgsConstructor
public class MarcheQualiteDto {

    @Id
    private Long id;

    private String name;
}
