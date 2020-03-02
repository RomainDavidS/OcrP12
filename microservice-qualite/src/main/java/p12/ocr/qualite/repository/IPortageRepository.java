package p12.ocr.qualite.repository;

import p12.ocr.qualite.model.Portage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPortageRepository extends JpaRepository<Portage,Long> {
}
