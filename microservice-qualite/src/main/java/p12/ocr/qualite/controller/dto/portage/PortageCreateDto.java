package p12.ocr.qualite.controller.dto.portage;

import p12.ocr.qualite.model.Bulletin;
import p12.ocr.qualite.model.MarcheQualite;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class PortageCreateDto {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date portageDate;

    private Bulletin bulletin;

    private MarcheQualite marcheQualite;


}
