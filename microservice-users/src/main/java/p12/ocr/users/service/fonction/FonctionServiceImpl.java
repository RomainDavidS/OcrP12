package p12.ocr.users.service.fonction;


import p12.ocr.users.model.Fonction;
import p12.ocr.users.repository.IFonctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FonctionServiceImpl implements IFonctionService {

    @Autowired
    private IFonctionRepository fonctionRepository;

    /**
     * Permet la recherche d'une fonction lié à  userentreprise
     * @param id identifiant de la fonction recherchée
     * @return Fonction si connu sinon null
     */
    public Fonction findById(Long id){ return fonctionRepository.findById( id ).orElse(null); }

    /**
     * Permet la recherche de la liste des fonctions userentreprise
     * @return List<Fonction>
     */
    public List<Fonction> findAll(){return fonctionRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d'une fonction
     * @param fonction Fonction à créer ou à mettre à jour
     * @return Fonction
     */
    public Fonction save(Fonction fonction){ return fonctionRepository.save( fonction ); }
}
