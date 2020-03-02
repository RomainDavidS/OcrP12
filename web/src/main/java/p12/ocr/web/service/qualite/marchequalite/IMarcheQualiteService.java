package p12.ocr.web.service.qualite.marchequalite;



import p12.ocr.web.beans.qualite.MarcheQualiteBean;

import java.util.List;

public interface IMarcheQualiteService {

    MarcheQualiteBean findById(Long id);
    List<MarcheQualiteBean> findAll();
    MarcheQualiteBean save(MarcheQualiteBean marche);
    void update(MarcheQualiteBean marche);
}
