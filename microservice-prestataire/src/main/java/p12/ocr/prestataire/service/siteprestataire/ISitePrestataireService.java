package p12.ocr.prestataire.service.siteprestataire;

import p12.ocr.prestataire.model.SitePrestataire;

import java.util.List;

public interface ISitePrestataireService {
    SitePrestataire findById(Long id);
    List<SitePrestataire> findAll();
    SitePrestataire save(SitePrestataire sitePrestataire);

}
