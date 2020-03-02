package p12.ocr.users.controller.dto.organization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter @Setter @NoArgsConstructor
public class OrganizationUpdateDto {

    @Id
    private Long id;

    private String name;


}
