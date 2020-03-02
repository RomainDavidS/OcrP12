package p12.ocr.web.service.marche.typemarche;

import p12.ocr.web.beans.marche.TypeMarcheBean;

import java.util.List;

public interface ITypeMarcheService {
    TypeMarcheBean findById(Long id);
    List<TypeMarcheBean> findAll();
    TypeMarcheBean save( TypeMarcheBean typeMarche);
    void update( TypeMarcheBean typeMarche);
}
