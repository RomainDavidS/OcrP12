package p12.ocr.web.service.prestataire.employee;



import p12.ocr.web.beans.prestataire.EmployeeBean;

import java.util.List;

public interface IEmployeeService {

    EmployeeBean findById(Long id);
    List<EmployeeBean> findAll();
    List<EmployeeBean> findAllActif(Long id);
    List<EmployeeBean> findAllInactif(Long id);
    EmployeeBean save(EmployeeBean employee);
    void update(EmployeeBean employee);
}
