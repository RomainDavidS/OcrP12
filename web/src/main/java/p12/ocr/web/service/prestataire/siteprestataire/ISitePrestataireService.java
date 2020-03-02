package p12.ocr.web.service.prestataire.siteprestataire;

import p12.ocr.web.beans.prestataire.SitePrestataireBean;

import java.util.List;

public interface ISitePrestataireService {
    SitePrestataireBean findById(Long id);
    List<SitePrestataireBean> findAll();
    SitePrestataireBean save(SitePrestataireBean site);
    void update(SitePrestataireBean site);
}
