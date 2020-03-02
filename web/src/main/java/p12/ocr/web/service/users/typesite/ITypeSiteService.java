package p12.ocr.web.service.users.typesite;

import p12.ocr.web.beans.users.TypeSiteBean;

import java.util.List;

public interface ITypeSiteService {

    TypeSiteBean findById(Long id);
    List<TypeSiteBean> findAll();
    TypeSiteBean save( TypeSiteBean typeSite);
    void update( TypeSiteBean typeSite);
}
