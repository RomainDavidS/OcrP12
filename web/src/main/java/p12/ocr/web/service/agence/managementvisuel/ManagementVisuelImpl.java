package p12.ocr.web.service.agence.managementvisuel;


import p12.ocr.web.beans.agence.ManagementVisuelBean;
import p12.ocr.web.proxies.IAgenceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementVisuelImpl implements IManagementVisuelService {

    @Autowired
    private IAgenceProxy agenceProxy;

    /**
     * Permet la recherche d'un management visuel
     * @param id Identifiant du management visuel recherché
     * @return ManagementVisuelBean si connu sinon null
     */
    public ManagementVisuelBean findById(String id){ return  agenceProxy.findManagementVisuelById(id );}

    /**
     * Permet la recherche de la liste des managements visuels
     * @return List<ManagementVisuelBean>
     */
    public List<ManagementVisuelBean> findAll(){return  agenceProxy.findAllManagementVisuel();}

    /**
     * Permet la création d'un management visuel
     * @param managementVisuel ManagementVisuelBean à créer
     * @return ManagementVisuelBean
     */
    public ManagementVisuelBean save(ManagementVisuelBean managementVisuel){ return agenceProxy.saveManagementVisuel( managementVisuel);}

    /**
     * Permet la mise à jour d'un management visuel
     * @param managementVisuel ManagementVisuel à mettre à jour
     */
    public void update(ManagementVisuelBean managementVisuel){ agenceProxy.updateManagementVisuel( managementVisuel);}

}
