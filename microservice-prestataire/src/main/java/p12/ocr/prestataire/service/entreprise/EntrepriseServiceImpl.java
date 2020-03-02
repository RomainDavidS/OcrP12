package p12.ocr.prestataire.service.entreprise;

import p12.ocr.prestataire.exception.ResourceNotFoundException;
import p12.ocr.prestataire.model.Entreprise;
import p12.ocr.prestataire.repository.IEntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

    @Autowired
    private IEntrepriseRepository entrepriseRepository;

    /**
     * Permet la recherche d'une entreprise prestataire
     * @param id Identifiant de l'entreprise recherchée
     * @return Entreprise si connu sinon null
     */
    public Entreprise findById(Long id) {return entrepriseRepository.findById(id).orElse(null);}

    /**
     * Permet la recherche d'une entreprise prestataire
     * @param siret Le numéro SIRET de l'entreprise recherchée
     * @return Entreprise
     */
    public Entreprise findBySiret(String siret){
        try {
            return  entrepriseRepository.findBySiret( siret );
        }catch (ResourceNotFoundException e){
            return null;
        }

    }

    /**
     * Permet la recherche de la liste des entreprises prestataires
     * @return List<Entreprise>
     */
    public List<Entreprise> findAll(){ return entrepriseRepository.findAll() ;}

    /**
     * Permet la création ou la mise à jour d'une entreprise prestataire
     * @param entreprise Entreprise à créer ou metter à jour
     * @return Entreprise
     */
    public Entreprise save(Entreprise entreprise ){return entrepriseRepository.save( entreprise );}

}
