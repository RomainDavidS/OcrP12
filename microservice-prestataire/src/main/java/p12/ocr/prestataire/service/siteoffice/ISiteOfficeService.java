package p12.ocr.prestataire.service.siteoffice;

import p12.ocr.prestataire.model.SiteOffice;

import java.util.List;

public interface ISiteOfficeService {

    SiteOffice findById(Long id);
    List<SiteOffice> findAll();
    SiteOffice save (SiteOffice siteOffice);
    void delete(Long id);
}
