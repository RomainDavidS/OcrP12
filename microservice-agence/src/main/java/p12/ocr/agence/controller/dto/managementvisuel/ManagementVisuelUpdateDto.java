package p12.ocr.agence.controller.dto.managementvisuel;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Id;
import javax.persistence.Lob;

@Getter @Setter @NoArgsConstructor
public class ManagementVisuelUpdateDto {

    @Id
    private String id;

    private String name;

    private boolean publication;
}
