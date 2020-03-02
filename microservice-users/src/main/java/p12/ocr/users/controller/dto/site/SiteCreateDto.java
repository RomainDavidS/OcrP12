package p12.ocr.users.controller.dto.site;

import p12.ocr.users.model.TypeSite;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public @Data class SiteCreateDto {

    private String name;

    private TypeSite typeSite;
}
