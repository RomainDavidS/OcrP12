package p12.ocr.qualite.repository;

import p12.ocr.qualite.model.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBulletinRepository extends JpaRepository<Bulletin,Long> {
}
