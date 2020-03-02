package p12.ocr.agence.controller.dto.managementvisuel;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Lob;

@Getter @Setter @NoArgsConstructor
public class ManagementVisuelCreateDto {

    private String name;

    private boolean publication;
}
