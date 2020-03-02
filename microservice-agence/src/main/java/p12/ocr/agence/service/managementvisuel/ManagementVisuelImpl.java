package p12.ocr.agence.service.managementvisuel;

import p12.ocr.agence.model.ManagementVisuel;
import p12.ocr.agence.repository.IManagementVisuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementVisuelImpl implements IManagementVisuelService {

    @Autowired
    private IManagementVisuelRepository managementVisuelRepository;

    /**
     * Permet la recherche d'un ManagementVisuel
     * @param id Identifiant du ManagementVisuel à rechercher
     * @return  ManagementVisuel si connu sinon null
     */
    public ManagementVisuel findById(String id){ return  managementVisuelRepository.findById(id ).orElse(null);}

    /**
     * Permet de rechercher la liste des ManagementVisuel
     * @return List<ManagementVisuel>
     */
    public List<ManagementVisuel> findAll(){return  managementVisuelRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d'un ManagementVisuel
     * @param managementVisuel ManagementVisuel à créer ou à mettre à jour
     * @return ManagementVisuel
     */
    public ManagementVisuel save(ManagementVisuel managementVisuel){ return managementVisuelRepository.save( managementVisuel);}



}
