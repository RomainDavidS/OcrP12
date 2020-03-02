package p12.ocr.users.repository;

import p12.ocr.users.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrganizationRepository extends JpaRepository<Organization,Long> {
}
