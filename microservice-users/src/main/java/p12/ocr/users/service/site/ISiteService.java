package p12.ocr.users.service.site;

import p12.ocr.users.model.Site;

import java.util.List;

public interface ISiteService {

    Site findById(Long id);
    List<Site> findAll();
    Site save(Site site);
}
