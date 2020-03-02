package p12.ocr.prestataire.controller.dto.callcenter;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter @NoArgsConstructor
public class CallCenterUpdateDto {

    @Id
    private Long id;

    private String name;

    private String phone;

    private String openingTime;

    private boolean enabled;
}
