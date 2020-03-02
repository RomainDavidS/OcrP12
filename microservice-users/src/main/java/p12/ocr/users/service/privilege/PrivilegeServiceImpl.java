package p12.ocr.users.service.privilege;

import p12.ocr.users.model.Privilege;
import p12.ocr.users.repository.IPrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeServiceImpl implements IPrivilegeService {

    @Autowired
    private IPrivilegeRepository privilegeRepository;

    /**
     * Permet la recherche d'un privilège  userentreprise
     * @param name nom du privilège recherché
     * @return Privilege si connu sinon null
     */
    public Privilege findByName(String name){ return  privilegeRepository.findByName( name );}

    /**
     * Permet la recherche de la liste des privilèges userentreprise
     * @return List<Privilege>
     */
    public List<Privilege> findAll(){return privilegeRepository.findAll();}

}
