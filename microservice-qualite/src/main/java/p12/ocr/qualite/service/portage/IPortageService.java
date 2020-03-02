package p12.ocr.qualite.service.portage;

import p12.ocr.qualite.model.Portage;

import java.util.List;

public interface IPortageService {

    Portage findById(Long id);
    List<Portage> findAll();
    Portage save (Portage portage);

}
