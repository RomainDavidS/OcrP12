package p12.ocr.prestataire.service.employee;

import p12.ocr.prestataire.model.Employee;
import p12.ocr.prestataire.model.SitePrestataire;
import p12.ocr.prestataire.repository.IEmployeeRepository;
import p12.ocr.prestataire.service.siteprestataire.ISitePrestataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private ISitePrestataireService sitePrestataireService;

    /**
     * Permet la recherche d'un employé de l'entreprise prestataire
     * @param id Identifiant de l'employé recherché
     * @return Employee si connu sinon null
     */
    public Employee findById(Long id) {return employeeRepository.findById(id).orElse(null);}

    /**
     * Permet la recherche de la liste des Employés de l'entreprise prestataire
     * @return List<Employee>
     */
    public List<Employee> findAll(){ return employeeRepository.findAll() ;}

    /**
     * Permet la recherche de la liste des Employés de l'entreprise prestataire toujours en activités
     * @param id
     * @return
     */
    public List<Employee> findAllActifBySite(Long id){
        SitePrestataire sitePrestataire = sitePrestataireService.findById( id );
        return employeeRepository.findAllBySitePrestataireAndEndDateContractIsNull( sitePrestataire ) ;
    }

    /**
     * Permet la recherche de la liste des Employés de l'entreprise prestataire qui ne sont plus en activités
     * @param id
     * @return
     */
    public List<Employee> findAllInactifBySite(Long id){
        SitePrestataire sitePrestataire = sitePrestataireService.findById( id );
        return employeeRepository.findAllBySitePrestataireAndEndDateContractIsNotNull( sitePrestataire ) ;
    }

    /**
     * Permet la création ou la mise à jour d'un employé de l'entreprise prestataire
     * @param employee Employee à créer ou à mettre à jour
     * @return Employee
     */
    public Employee save(Employee employee ){return employeeRepository.save( employee );}


}
