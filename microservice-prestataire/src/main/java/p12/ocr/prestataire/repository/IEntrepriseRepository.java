package p12.ocr.prestataire.repository;

import p12.ocr.prestataire.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEntrepriseRepository extends JpaRepository<Entreprise,Long> {

    Entreprise findBySiret(String siret);
}
