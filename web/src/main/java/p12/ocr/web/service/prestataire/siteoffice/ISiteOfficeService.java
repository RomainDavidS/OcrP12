package p12.ocr.web.service.prestataire.siteoffice;

import p12.ocr.web.beans.prestataire.SiteOfficeBean;

import java.util.List;

public interface ISiteOfficeService {

    SiteOfficeBean findById(Long id);
    List<SiteOfficeBean> findAll();
    SiteOfficeBean save (SiteOfficeBean siteOfficeBean);
    void update(SiteOfficeBean siteOfficeBean);
    void delete(Long id);
}
