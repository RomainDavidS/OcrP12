package p12.ocr.web.service.users.site;

import p12.ocr.web.beans.users.SiteEntrepriseBean;

import java.util.List;

public interface ISiteEntrepriseService {
    SiteEntrepriseBean findById(Long id);
    List<SiteEntrepriseBean> findAll();
    SiteEntrepriseBean save(SiteEntrepriseBean site);
    void update( SiteEntrepriseBean site);
}
