package p12.ocr.marche.service.indemnisation;



import p12.ocr.marche.model.Indemnisation;

import java.util.List;

public interface IIndemnisationService {

    Indemnisation findById(Long id);
    List<Indemnisation> findAll();
    Indemnisation save(Indemnisation indemnisation);
}
