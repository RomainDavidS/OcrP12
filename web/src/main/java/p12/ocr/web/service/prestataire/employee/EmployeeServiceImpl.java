package p12.ocr.web.service.prestataire.employee;


import p12.ocr.web.beans.prestataire.EmployeeBean;
import p12.ocr.web.proxies.IPrestataireProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IPrestataireProxy prestataireProxy;

    /**
     * Permet la recherche d'un employé d'une entreprise prestataire
     * @param id Identifiant de l'employé recherché
     * @return EmployeeBean si connu sinon numm
     */
    public EmployeeBean findById(Long id) {return prestataireProxy.findEmployeeById(id);}

    /**
     * Permet la recherche des employés des entreprises prestataires
     * @return List<EmployeeBean>
     */
    public List<EmployeeBean> findAll(){ return prestataireProxy.findAllEmployee() ;}

    /**
     * Permet la recherche des employés en activités d'un marché
     * @param id Identifiant du marché
     * @return List<EmployeeBean>
     */
    public List<EmployeeBean> findAllActif(Long id){ return prestataireProxy.findAllEmployeeActif( id ) ;}

    /**
     * Permet la recherche des employés en inactivités d'un marché
     * @param id Identifiant du marché
     * @return List<EmployeeBean>
     */
    public List<EmployeeBean> findAllInactif(Long id){ return prestataireProxy.findAllEmployeeInactif( id ) ;}

    /**
     * Permet la création d'un employé d'une enteprise prestataire
     * @param employee EmployeeBean à créer
     * @return EmployeeBean
     */
    public EmployeeBean save(EmployeeBean employee ){return prestataireProxy.saveEmployee( employee );}

    /**
     * Permet de mettre à jour un employé d'une entreprise prestataire
     * @param employee EmployeeBean à mettre à jour
     */
    public void update(EmployeeBean employee ){prestataireProxy.updateEmployee( employee );}
}
