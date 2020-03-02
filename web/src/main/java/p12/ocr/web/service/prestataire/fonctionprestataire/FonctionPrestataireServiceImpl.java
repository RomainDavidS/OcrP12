package p12.ocr.web.service.prestataire.fonctionprestataire;

import p12.ocr.web.beans.prestataire.FonctionPrestataireBean;
import p12.ocr.web.proxies.IPrestataireProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FonctionPrestataireServiceImpl implements IFonctionPrestataireService {

    @Autowired
    private IPrestataireProxy prestataireProxy;

    /**
     * Permet la recherche d'une fonction des entreprises prestataires
     * @param id Identifiant de la fonction
     * @return FonctionPrestataireBean si connu sinon null
     */
    public FonctionPrestataireBean findById(Long id ){ return prestataireProxy.findFonctionPrestataireById( id );}

    /**
     * Permet la recherche de la liste des fonctions des entreprises prestataires
     * @return List<FonctionPrestataireBean>
     */
    public List<FonctionPrestataireBean> findAll(){return prestataireProxy.findAllFonctionPrestataire();}

    /**
     * Permet la création d'une fonction des entreprises prestataire
     * @param fonctionPrestataireBean FonctionPrestataireBean à créer
     * @return FonctionPrestataireBean
     */
    public FonctionPrestataireBean save(FonctionPrestataireBean fonctionPrestataireBean ){return prestataireProxy.saveFonctionPrestataire( fonctionPrestataireBean );}

    /**
     * Permet la mise à jour d'une fonction des entreprises prestataires
     * @param fonctionPrestataireBean FonctionPrestataireBean
     */
    public void update(FonctionPrestataireBean fonctionPrestataireBean ){ prestataireProxy.updateFontionPrestataire( fonctionPrestataireBean);}
}
