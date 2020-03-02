package p12.ocr.users.controller.dto.site;

import p12.ocr.users.model.TypeSite;
import lombok.*;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
public @Data class SiteUpdateDto {

    @Id
    private Long id;

    private String name;

    private TypeSite typeSite;
}
