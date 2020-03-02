package p12.ocr.users.service.typesite;

import p12.ocr.users.model.TypeSite;

import java.util.List;

public interface ITypeSiteService {

    TypeSite findById(Long id );
    List<TypeSite> findAll();
    TypeSite save (TypeSite typeSite );
}
