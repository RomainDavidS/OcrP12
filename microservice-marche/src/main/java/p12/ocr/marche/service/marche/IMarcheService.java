package p12.ocr.marche.service.marche;

import p12.ocr.marche.model.Marche;

import java.util.List;

public interface IMarcheService {

    Marche findById(Long id);
    List<Marche> findAll();
    Marche save(Marche marche );
}
