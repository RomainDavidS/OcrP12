package p12.ocr.users.controller.dto.typesite;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
public class TypeSiteUpdateDto {

    @Id
    private Long id;

    private String name;
}
