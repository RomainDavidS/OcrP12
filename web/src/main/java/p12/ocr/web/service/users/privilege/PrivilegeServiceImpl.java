package p12.ocr.web.service.users.privilege;

import p12.ocr.web.beans.users.PrivilegeBean;
import p12.ocr.web.proxies.IUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeServiceImpl implements IPrivilegeService {
    @Autowired
    private IUsersProxy usersProxy;

    /**
     * Permet la recherche d'un privilège d'un user
     * @param name Nom du privilège recherché
     * @return PrivilegeBean
     */
    public PrivilegeBean findByName( String name){
        return usersProxy.findPrivilegeByName( name );
    }

    /**
     * Permet la recherche de la liste des privilèges des user
     * @return List<PrivilegeBean>
     */
    public List<PrivilegeBean> findAll(){
        return usersProxy.findAllPrivilege();
    }
}


