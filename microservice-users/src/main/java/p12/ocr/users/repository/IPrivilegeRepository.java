package p12.ocr.users.repository;

import p12.ocr.users.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrivilegeRepository extends JpaRepository<Privilege,Long> {

    Privilege findByName(String name);
}
