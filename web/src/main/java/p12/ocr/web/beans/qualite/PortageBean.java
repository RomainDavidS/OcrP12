package p12.ocr.web.beans.qualite;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class PortageBean {

    @Id
    private Long id;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date portageDate;

    private BulletinBean bulletin;

    private MarcheQualiteBean marcheQualite;

    public PortageBean(BulletinBean bulletin, MarcheQualiteBean marcheQualite) {
        this.bulletin = bulletin;
        this.marcheQualite = marcheQualite;
    }
}
