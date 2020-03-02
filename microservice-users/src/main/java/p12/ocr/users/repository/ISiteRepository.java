package p12.ocr.users.repository;

import p12.ocr.users.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISiteRepository extends JpaRepository<Site,Long> {
}
