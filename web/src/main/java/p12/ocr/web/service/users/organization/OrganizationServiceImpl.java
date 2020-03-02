package p12.ocr.web.service.users.organization;

import p12.ocr.web.beans.users.OrganizationBean;
import p12.ocr.web.proxies.IUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements IOrganizationService {
    @Autowired
    private IUsersProxy usersProxy;

    /**
     * Permet la recherche d'une organisation d'un user
     * @param id Identifiant de l'organisation recherché
     * @return OrganizationBean si connu sinon null
     */
    public OrganizationBean findById(Long id){
        return usersProxy.findOrganizationById( id );
    }

    /**
     * Permet la recherche des organisations des user
     * @return List<OrganizationBean>
     */
    public List<OrganizationBean> findAll(){
        return usersProxy.findAllOrganization();
    }

    /**
     * Permet la création d'une organisation d'un user
     * @param organization Organization à créer
     * @return OrganizationBean
     */
    public OrganizationBean save( OrganizationBean organization){return usersProxy.saveOrganization( organization );}

    /**
     * Permet de mettre à jour l'organisation d'un user
     * @param organization
     */
    public void update( OrganizationBean organization){
        usersProxy.updateOrganization( organization );
    }
}
