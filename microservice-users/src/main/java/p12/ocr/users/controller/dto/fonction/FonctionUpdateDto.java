package p12.ocr.users.controller.dto.fonction;

import lombok.*;

import javax.persistence.*;


@Getter @Setter @NoArgsConstructor
public class FonctionUpdateDto {

    @Id
    private Long id;

    private String name;

}
