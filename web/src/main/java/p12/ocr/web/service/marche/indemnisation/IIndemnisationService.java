package p12.ocr.web.service.marche.indemnisation;

import p12.ocr.web.beans.marche.IndemnisationBean;

import java.util.List;

public interface IIndemnisationService {

    IndemnisationBean findById(Long id);
    List<IndemnisationBean> findAll();
    IndemnisationBean save(IndemnisationBean indemnisation);
    void update(IndemnisationBean indemnisation);
}
