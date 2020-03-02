package p12.ocr.prestataire.repository;

import p12.ocr.prestataire.model.SitePrestataire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISitePrestataireRepository extends JpaRepository<SitePrestataire,Long> {
}
