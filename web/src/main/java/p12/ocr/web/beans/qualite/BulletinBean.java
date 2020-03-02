package p12.ocr.web.beans.qualite;


import p12.ocr.web.technical.enums.typebulletin.TypeBulletin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class BulletinBean {
    @Id
    private Long id;

    @NotBlank(message = "Le bulletin est obligatoire")
    private String idBulletin;

    @NotBlank(message = "Le nom du bulletin est obligatoire")
    private String name;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private TypeBulletin typeBulletin;

    private List<PortageBean> portageList;
}
