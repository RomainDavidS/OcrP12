package p12.ocr.prestataire.service.fonctionprestataire;

import p12.ocr.prestataire.model.FonctionPrestataire;

import java.util.List;

public interface IFonctionPrestataireService {

    FonctionPrestataire findById(Long id);
    List<FonctionPrestataire> findAll();
    FonctionPrestataire save(FonctionPrestataire fonctionPrestataire);


}
