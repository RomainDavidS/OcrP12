package p12.ocr.prestataire.repository;

import p12.ocr.prestataire.model.Employee;
import p12.ocr.prestataire.model.SitePrestataire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findAllBySitePrestataireAndEndDateContractIsNull(SitePrestataire sitePrestataire );
    List<Employee> findAllBySitePrestataireAndEndDateContractIsNotNull(SitePrestataire sitePrestataire );
}
