package p12.ocr.prestataire.controller.dto.callcenter;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter @Setter @NoArgsConstructor
public class CallCenterCreateDto {

    private String name;

    private String phone;

    private String openingTime;

    private boolean enabled;
}
