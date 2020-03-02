package p12.ocr.qualite.service.marchequalite;

import p12.ocr.qualite.model.MarcheQualite;

import java.util.List;

public interface IMarcheQualiteService {

    MarcheQualite findById(Long id);
    List<MarcheQualite> findAll();
    MarcheQualite save (MarcheQualite marcheQualite);
}
