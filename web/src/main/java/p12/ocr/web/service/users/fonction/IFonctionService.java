package p12.ocr.web.service.users.fonction;

import p12.ocr.web.beans.users.FonctionBean;

import java.util.List;

public interface IFonctionService {

    FonctionBean findById(Long id);
    List<FonctionBean> findAll();
    FonctionBean save( FonctionBean fonction);
    void update( FonctionBean fonction);
}
