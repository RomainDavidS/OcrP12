package p12.ocr.prestataire.service.entreprise;

import p12.ocr.prestataire.model.Entreprise;

import java.util.List;

public interface IEntrepriseService {

    Entreprise findById(Long id);
    Entreprise findBySiret(String siret);
    List<Entreprise> findAll();
    Entreprise save(Entreprise entreprise );
}
