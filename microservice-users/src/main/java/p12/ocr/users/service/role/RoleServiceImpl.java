package p12.ocr.users.service.role;

import p12.ocr.users.model.Role;
import p12.ocr.users.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    /**
     * Permet la recherche d'un rôle userentreprise
     * @param id identifiant du rôle recherché
     * @return Role si connu sinon null
     */
    public Role findById(Long id){ return roleRepository.findById( id).orElse(null);}

    /**
     * Permet la recherche de la liste des rôles userentreprise
     * @return List<Role>
     */
    public List<Role> findAll(){return  roleRepository.findAll();}
}
