package p12.ocr.users.service.userentreprise;

import p12.ocr.users.exception.ResourceNotFoundException;
import p12.ocr.users.model.UserEntreprise;
import p12.ocr.users.repository.IUserEntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntrepriseServiceImpl implements IUserEntrepriseService {

    @Autowired
    private IUserEntrepriseRepository userEntrepriseRepository;

    /**
     * Permet la recherche d'un user entreprise
     * @param id identifiant de l'user entreprise recherché
     * @return UserEntreprise si connu sinon null
     */
    public UserEntreprise findById(Long id){return userEntrepriseRepository.findById( id).orElse(null);}

    /**
     * Permet la recherche d'un user entreprise
     * @param nni nni de l'user entreprise recherché
     * @return UserEntreprise si connu sinon null
     */
    public UserEntreprise findByNni(String nni ){
        try {
            return userEntrepriseRepository.findByNni( nni );
        }catch (ResourceNotFoundException e){
            return null;
        }


    }

    /**
     * Permet la recherche de la liste des userentreprise
     * @return List<UserEntreprise>
     */
    public List<UserEntreprise> findAll(){return userEntrepriseRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d'un user entreprise
     * @param userEntreprise UserEntreprise à créer ou à mettre à jour
     * @return UserEntreprise
     */
    public UserEntreprise save(UserEntreprise userEntreprise){ return userEntrepriseRepository.save(userEntreprise); }





}
