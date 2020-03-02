package p12.ocr.web.service.prestataire.entreprise;



import p12.ocr.web.beans.prestataire.EntrepriseBean;
import p12.ocr.web.beans.prestataire.SiretEntrepriseBean;
import p12.ocr.web.technical.api.siret.ApiInsee;

import java.util.List;

public interface IEntrepriseService {

    EntrepriseBean findById(Long id);
    EntrepriseBean findBySiret(String siret);
    List<EntrepriseBean> findAll();
    EntrepriseBean save(EntrepriseBean entreprise);
    void update(EntrepriseBean entreprise);
    boolean isExisting(EntrepriseBean entrepriseBean);

    SiretEntrepriseBean searchSiret(ApiInsee apiInsee);
}
