package p12.ocr.marche.repository;

import p12.ocr.marche.model.Marche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMarcheRepository extends JpaRepository<Marche,Long> {
}
