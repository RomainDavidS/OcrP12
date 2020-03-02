package p12.ocr.web.service.prestataire.siteoffice;

import p12.ocr.web.beans.prestataire.SiteOfficeBean;
import p12.ocr.web.proxies.IPrestataireProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteOfficeServiceImpl implements ISiteOfficeService {

    @Autowired
    private IPrestataireProxy prestataireProxy;

    /**
     * Permet la recherche d'un employé RH d'un site prestataire
     * @param id Identifiant de l'employé
     * @return SiteOfficeBean
     */
    public SiteOfficeBean findById(Long id){return prestataireProxy.findSiteOfficeById(id);}

    /**
     * Permet la recherche de la listes des employés RH des entreprises prestataires
     * @return List<SiteOfficeBean>
     */
    public List<SiteOfficeBean> findAll(){return  prestataireProxy.findAllSiteOffice();}

    /**
     * Permet la création d'un employé RH d'un site prestataire
     * @param siteOfficeBean SiteOfficeBean à créer
     * @return SiteOfficeBean
     */
    public SiteOfficeBean save (SiteOfficeBean siteOfficeBean){return prestataireProxy.saveSiteOffice( siteOfficeBean );}

    /**
     * Permet de mettre à jour un employé d'un site prestataire
     * @param siteOfficeBean SiteOfficeBean à mettre à jour
     */
    public void update(SiteOfficeBean siteOfficeBean){prestataireProxy.saveSiteOffice(siteOfficeBean);}

    /**
     * Permet de supprimer un employé RH d'un site prestataire
     * @param id Identifiant de l'employé
     */
    public void delete(Long id){prestataireProxy.deleteSiteOffice(id);}
}
