package p12.ocr.marche.repository;

import p12.ocr.marche.model.TypeMarche;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeMarcheRepository extends JpaRepository<TypeMarche,Long> {
}
