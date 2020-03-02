package p12.ocr.web.service.users.site;

import p12.ocr.web.beans.users.SiteEntrepriseBean;
import p12.ocr.web.beans.users.TypeSiteBean;
import p12.ocr.web.proxies.IUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteEntrepriseServiceImpl implements ISiteEntrepriseService {

    @Autowired
    private IUsersProxy usersProxy;

    /**
     * Permet la recherche d'un site des user
     * @param id Identifiant du site recherché
     * @return SiteEntrepriseBean si connu sinon null
     */
    public SiteEntrepriseBean findById(Long id){
        return usersProxy.findSiteById( id );
    }

    /**
     * Permet la recherche de la liste des sites des user
     * @return List<SiteEntrepriseBean>
     */
    public List<SiteEntrepriseBean> findAll(){return usersProxy.findAllSite();}

    /**
     * Permet la création d'un site des user
     * @param site Site à créer
     * @return SiteEntrepriseBean
     */
    public SiteEntrepriseBean save(SiteEntrepriseBean site){
        TypeSiteBean typeSiteBean = usersProxy.findTypeSiteById( site.getTypeSite().getId() );
        site.setTypeSite( typeSiteBean );
        return usersProxy.saveSite( site );
    }

    /**
     * Peremt la mise à jour d'un site des users
     * @param site Site à mettre à jour
     */
    public void update( SiteEntrepriseBean site){
        TypeSiteBean typeSiteBean = usersProxy.findTypeSiteById( site.getTypeSite().getId() );
        site.setTypeSite( typeSiteBean );
        usersProxy.updateSite( site );
    }
}
