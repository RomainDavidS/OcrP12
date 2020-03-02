package p12.ocr.users.service.organization;

import p12.ocr.users.model.Organization;
import p12.ocr.users.repository.IOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private IOrganizationRepository organizationRepository;

    /**
     * Permet la recherche d'une organisation lié un user entreprise
     * @param id identifiant de l'organisation recherchée
     * @return Fonction si connu sinon null
     */
    public Organization findById(Long id){return organizationRepository.findById( id ).orElse(null);}

    /**
     *  Permet la recherche de la liste des organisations
     * @return List<Organization>
     */
    public List<Organization> findAll(){return  organizationRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d'une organisation
     * @param organization Organization à créer ou à mettre à jour
     * @return Organization
     */
    public Organization save(Organization organization ){ return  organizationRepository.save( organization );}
}
