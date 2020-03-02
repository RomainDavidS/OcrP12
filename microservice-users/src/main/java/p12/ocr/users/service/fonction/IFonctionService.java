package p12.ocr.users.service.fonction;

import p12.ocr.users.model.Fonction;

import java.util.List;

public interface IFonctionService {

    Fonction findById(Long id);
    List<Fonction> findAll();
    Fonction save(Fonction fonction);
}
