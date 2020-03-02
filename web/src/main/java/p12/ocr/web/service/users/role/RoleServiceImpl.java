package p12.ocr.web.service.users.role;

import p12.ocr.web.beans.users.RoleBean;
import p12.ocr.web.proxies.IUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService{

    @Autowired
    private IUsersProxy usersProxy;

    /**
     * Permet la recherche d'un rôle d'un user
     * @param id Identifiant du rôle recherché
     * @return RoleBean si connu sinon null
     */
    public RoleBean findById(Long id){
        return usersProxy.findRoleById( id );
    }

    /**
     * Permet la recherche la liste des rôles des user
     * @return List<RoleBean>
     */
    public List<RoleBean> findAll(){ return usersProxy.findAllRole();}
}
