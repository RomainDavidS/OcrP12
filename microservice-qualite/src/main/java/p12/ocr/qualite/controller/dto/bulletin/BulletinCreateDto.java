package p12.ocr.qualite.controller.dto.bulletin;

import p12.ocr.qualite.model.Portage;
import p12.ocr.qualite.technical.enums.typebulletin.TypeBulletin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BulletinCreateDto {
    private String idBulletin;

    private String name;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private TypeBulletin typeBulletin;

    private List<Portage> portageList;
}
