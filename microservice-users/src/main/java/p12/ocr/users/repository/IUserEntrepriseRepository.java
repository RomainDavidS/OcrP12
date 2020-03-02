package p12.ocr.users.repository;

import p12.ocr.users.model.UserEntreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserEntrepriseRepository extends JpaRepository<UserEntreprise,Long> {

    UserEntreprise findByNni(String nni );
}
