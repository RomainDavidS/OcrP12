package p12.ocr.users.service.typesite;

import p12.ocr.users.model.TypeSite;
import p12.ocr.users.repository.ITypeSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeSiteServiceImpl implements ITypeSiteService {

    @Autowired
    private ITypeSiteRepository typeSiteRepository;

    /**
     * Permet la recherche d'un type de site lié à l'userentreprise
     * @param id identifiant du type de site de l'usernedis recherché
     * @return TypeSite si connu sinon null
     */
    public TypeSite findById(Long id ){ return typeSiteRepository.findById( id ).orElse(null ); }

    /**
     * Permet la recherche de la liste des types de sites userentreprise
     * @return List<TypeSite>
     */
    public List<TypeSite> findAll(){ return  typeSiteRepository.findAll(); }

    /**
     * Permet la création ou la mise à jour d'un type de site
     * @param typeSite TypeSite à créer ou à mettre à jour
     * @return TypeSite
     */
    public TypeSite save (TypeSite typeSite ){ return  typeSiteRepository.save( typeSite );}
}
