package p12.ocr.web.beans.qualite;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class MarcheQualiteBean {

    @Id
    private Long id;

    private String name;
}
