package p12.ocr.prestataire.service.employee;

import p12.ocr.prestataire.model.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee findById(Long id);
    List<Employee> findAll();
    List<Employee> findAllActifBySite(Long id);
    List<Employee> findAllInactifBySite(Long id);
    Employee save(Employee employee );

}
