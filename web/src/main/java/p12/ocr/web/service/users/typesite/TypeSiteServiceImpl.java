package p12.ocr.web.service.users.typesite;

import p12.ocr.web.beans.users.TypeSiteBean;
import p12.ocr.web.proxies.IUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeSiteServiceImpl implements ITypeSiteService {

    @Autowired
    private IUsersProxy usersProxy;

    /**
     * Permet la recherche d'un type de site des users
     * @param id Identifiant du type de site recherché
     * @return
     */
    public TypeSiteBean findById(Long id){
        return usersProxy.findTypeSiteById( id );
    }

    /**
     * Permet la recherche de la liste des types de site des users
     * @return List<TypeSiteBean>
     */
    public List<TypeSiteBean> findAll(){ return usersProxy.findAllTypeSite();}

    /**
     * Permet la création d'un type de site des users
     * @param typeSite TypeSite à créer
     * @return TypeSiteBean
     */
    public TypeSiteBean save( TypeSiteBean typeSite){ return usersProxy.saveTypeSite( typeSite );}

    /**
     * Permet la mise à jour d'un type de site des users
     * @param typeSite TypeSite à mettre à jour
     */
    public void update( TypeSiteBean typeSite){usersProxy.updateTypeSite( typeSite );}
}
