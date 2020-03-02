package p12.ocr.web.service.marche.marche;

import p12.ocr.web.beans.marche.MarcheBean;
import p12.ocr.web.technical.menu.marche.MenuOrganization;

import java.util.List;

public interface IMarcheService {
    MarcheBean findById(Long id);
    List<MarcheBean> findAll();
    MarcheBean save( MarcheBean marche);
    void update( MarcheBean marche);
    List<MenuOrganization> getMenuMarche();
}
