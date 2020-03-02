package p12.ocr.web.technical.api.siret;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SiretEntreprise {

    private Header header;

    private Etablissement etablissement;

}
