package p12.ocr.web.service.marche.indemnisation;


import p12.ocr.web.beans.marche.IndemnisationBean;
import p12.ocr.web.proxies.IMarcheProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndemnisationServiceImpl implements IIndemnisationService {

    @Autowired
    private IMarcheProxy marcheProxy;

    /**
     * Permet la recherche d'une indemnisation
     * @param id Identifiant de l'indemnisation recherchée
     * @return IndemnisationBean si connu sinon null
     */
    public IndemnisationBean findById(Long id){ return marcheProxy.findIndemnisationById( id);}

    /**
     * Permet la recherche de la liste des indemnisations
     * @return List<IndemnisationBean>
     */
    public List<IndemnisationBean> findAll(){return marcheProxy.findAllIndemnisation();}

    /**
     * Permet la création d'une indemnisation
     * @param indemnisation IndemnisationBean à créer
     * @return IndemnisationBean
     */
    public IndemnisationBean save(IndemnisationBean indemnisation){return  marcheProxy.saveIndemnisation( indemnisation );}

    /**
     * permet la mise à jour d'une indemnisation
     * @param indemnisation IndemnisatioBean à mettre à jour
     */
    public void update(IndemnisationBean indemnisation){marcheProxy.updateIndeminisation( indemnisation );}
}
