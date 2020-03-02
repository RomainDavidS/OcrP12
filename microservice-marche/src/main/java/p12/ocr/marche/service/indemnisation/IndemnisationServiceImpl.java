package p12.ocr.marche.service.indemnisation;


import p12.ocr.marche.model.Indemnisation;
import p12.ocr.marche.repository.IIndemnisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndemnisationServiceImpl implements IIndemnisationService {

    @Autowired
    private IIndemnisationRepository indemnisationRepository;

    /**
     * Permet la recherche d'une indemnisation
     * @param id Identifiant de l'indemnisation recherchée
     * @return Indemnisation si connu sinon null
     */
    public Indemnisation findById(Long id){ return indemnisationRepository.findById( id).orElse(null);}

    /**
     * Permet la recherche de la liste des indemnisations
     * @return List<Indemnisation>
     */
    public List<Indemnisation> findAll(){return indemnisationRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d' une Indemnisation
     * @param indemnisation Indemnisation à créer ou à mettre à jour
     * @return Indemnisation
     */
    public Indemnisation save(Indemnisation indemnisation){return  indemnisationRepository.save( indemnisation );}
}
