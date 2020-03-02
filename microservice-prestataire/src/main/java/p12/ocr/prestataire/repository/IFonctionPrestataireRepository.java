package p12.ocr.prestataire.repository;

import p12.ocr.prestataire.model.FonctionPrestataire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFonctionPrestataireRepository extends JpaRepository<FonctionPrestataire,Long> {
}
