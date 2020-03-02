package p12.ocr.prestataire.service.siteprestataire;

import p12.ocr.prestataire.model.SitePrestataire;
import p12.ocr.prestataire.repository.ISitePrestataireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SitePrestataireServiceImpl implements ISitePrestataireService {

    @Autowired
    private ISitePrestataireRepository siteRepository;

    /**
     * Permet la recherche d'un site de l'entreprise prestataire
     * @param id Identifiant du site recherché
     * @return SitePrestataire si connu sinon null
     */
    public SitePrestataire findById(Long id) {return siteRepository.findById(id).orElse(null);}

    /**
     * Permet la recherche de la liste des sites des entreprises prestataires
     * @return List<SitePrestataire>
     */
    public List<SitePrestataire> findAll(){ return siteRepository.findAll() ;}

    /**
     * Permet la création ou la mise à jour d'un site de l'entreprise prestataire
     * @param sitePrestataire SitePrestataire à créer ou à mettre à jour
     * @return SitePrestataire
     */
    public SitePrestataire save(SitePrestataire sitePrestataire){return siteRepository.save(sitePrestataire);}

}
