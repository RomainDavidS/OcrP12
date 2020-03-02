package p12.ocr.users.service.site;

import p12.ocr.users.model.Site;
import p12.ocr.users.repository.ISiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements  ISiteService {

    @Autowired
    private ISiteRepository siteRepository;

    /**
     * Permet la recherche d'un site lié à l'userentreprise
     * @param id identifiant du site de l'usernedis recherché
     * @return Site si connu sinon null
     */
    public Site findById(Long id){ return siteRepository.findById( id).orElse(null);}

    /**
     * Permet la recherche de la liste des sites userentreprise
     * @return List<Site>
     */
    public List<Site> findAll(){return siteRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d'un site
     * @param site Site à créer ou à mettre à jour
     * @return Site
     */
    public Site save(Site site){ return  siteRepository.save( site );}
}
