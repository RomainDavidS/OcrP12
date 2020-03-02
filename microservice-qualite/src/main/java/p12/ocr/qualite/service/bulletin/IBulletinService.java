package p12.ocr.qualite.service.bulletin;

import p12.ocr.qualite.model.Bulletin;

import java.util.List;

public interface IBulletinService {

    Bulletin findById(Long id);
    List<Bulletin> findAll();
    Bulletin save (Bulletin bulletin);
}
