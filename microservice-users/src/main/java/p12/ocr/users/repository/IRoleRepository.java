package p12.ocr.users.repository;

import p12.ocr.users.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name );
}
