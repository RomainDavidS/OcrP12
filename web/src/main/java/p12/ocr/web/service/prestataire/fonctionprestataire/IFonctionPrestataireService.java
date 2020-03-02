package p12.ocr.web.service.prestataire.fonctionprestataire;

import p12.ocr.web.beans.prestataire.FonctionPrestataireBean;

import java.util.List;

public interface IFonctionPrestataireService {
    FonctionPrestataireBean findById(Long id );
    List<FonctionPrestataireBean> findAll();
    FonctionPrestataireBean save(FonctionPrestataireBean fonctionPrestataireBean );
    void update(FonctionPrestataireBean fonctionPrestataireBean );
}
