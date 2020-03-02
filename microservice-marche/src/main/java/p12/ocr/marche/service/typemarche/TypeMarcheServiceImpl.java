package p12.ocr.marche.service.typemarche;

import p12.ocr.marche.model.TypeMarche;
import p12.ocr.marche.repository.ITypeMarcheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeMarcheServiceImpl implements ITypeMarcheService {

    @Autowired
    private ITypeMarcheRepository typeMarcheRepository;

    /**
     * Permet la recherche d'un type de marché
     * @param id Identifiant du type de marché recherché
     * @return TypeMarche si connu sinon null
     */
    public TypeMarche findById(Long id){ return  typeMarcheRepository.findById( id).orElse(null);}

    /**
     * Permet la recherhche de la liste des types de marché
     * @return List<TypeMarche>
     */
    public List<TypeMarche> findAll(){return  typeMarcheRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d'un type de marché
     * @param typeMarche TypeMarche à créer ou à mettre à jour
     * @return TypeMarche
     */
    public TypeMarche save(TypeMarche typeMarche ){
        return typeMarcheRepository.save( typeMarche );
    }


}
