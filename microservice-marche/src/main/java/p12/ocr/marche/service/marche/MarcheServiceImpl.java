package p12.ocr.marche.service.marche;

import p12.ocr.marche.model.Marche;
import p12.ocr.marche.repository.IMarcheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcheServiceImpl implements IMarcheService {

    @Autowired
    private IMarcheRepository marcheRepository;

    /**
     * Permet la recherche d'un marché
     * @param id Identifiant du marché recherché
     * @return Marche si connu sinon null
     */
    public Marche findById(Long id){ return  marcheRepository.findById( id).orElse(null);}

    /***
     * Permet la recherche de la liste des marchés
     * @return List<Marche>
     */
    public List<Marche> findAll(){return  marcheRepository.findAll(
            Sort.by( Sort.Direction.DESC,"typeMarche" )
                    .and(Sort.by( Sort.Direction.ASC, "idOrganization") )
                    .and(Sort.by( Sort.Direction.ASC, "name") ) );
    }

    /**
     * Permet la création ou la mise à jour d'un marché
      * @param marche Marche à créer ou à mettre à jour
     * @return Marche
     */
    public Marche save(Marche marche ){
        return marcheRepository.save( marche );
    }


}
