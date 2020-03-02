package p12.ocr.web.service.users.fonction;

import p12.ocr.web.beans.users.FonctionBean;
import p12.ocr.web.proxies.IUsersProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FonctionServiceImpl implements IFonctionService {

    @Autowired
    private IUsersProxy usersProxy;

    /**
     * Permet la recherche d'une fonction d'un userentreprise
     * @param id Identifiant de la fonction recherché
     * @return FonctionBean si connu sinon null
     */
    public FonctionBean findById(Long id){
        return  usersProxy.findFonctionById( id );
    }

    /**
     * Permet la recherche de la liste des fonctions des user entreprise
     * @return List<FonctionBean>
     */
    public List<FonctionBean> findAll(){
        return usersProxy.findAllFonction();
    }

    /**
     * Permet la création d'une fonction d'un userentreprise
     * @param fonction Fonction à créer
     * @return FonctionBean
     */
    public FonctionBean save( FonctionBean fonction){
        return usersProxy.saveFonction( fonction );
    }

    /**
     * Permet la mise à jour d'une fonction d'userendis
     * @param fonction Fonction à mettre à jour
     */
    public void update( FonctionBean fonction){
        usersProxy.updateFonction( fonction );
    }
}
