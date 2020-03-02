package p12.ocr.web.service.prestataire.siteprestataire;


import p12.ocr.web.beans.prestataire.SitePrestataireBean;
import p12.ocr.web.proxies.IPrestataireProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SitePrestataireServiceImpl implements ISitePrestataireService {

    @Autowired
    private IPrestataireProxy prestataireProxy;

    /**
     * Permet la recherche d'un site prestataire
     * @param id Identifiant du site recherché
     * @return SitePrestataireBean
     */
    public SitePrestataireBean findById(Long id) {return prestataireProxy.findSiteById(id);}

    /**
     * Permet la recherche de la liste des sites prestataires
     * @return List<SitePrestataireBean>
     */
    public List<SitePrestataireBean> findAll(){ return prestataireProxy.findAllSite() ;}

    /**
     * Permet la création d'un site prestataire
     * @param site SitePrestataireBean à créer
     * @return SitePrestataireBean
     */
    public SitePrestataireBean save(SitePrestataireBean site ){
        return prestataireProxy.saveSite( site );
    }

    /**
     * Permet de mettre à jour un site prestataire
     * @param site SitePrestataire
     */
    public void update(SitePrestataireBean site ){prestataireProxy.updateSite( site ); }

}
