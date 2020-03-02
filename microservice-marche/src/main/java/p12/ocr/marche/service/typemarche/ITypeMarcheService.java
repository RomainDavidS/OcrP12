package p12.ocr.marche.service.typemarche;

import p12.ocr.marche.model.TypeMarche;

import java.util.List;

public interface ITypeMarcheService {
    TypeMarche findById(Long id);
    List<TypeMarche> findAll();
    TypeMarche save(TypeMarche typeMarche );
}
