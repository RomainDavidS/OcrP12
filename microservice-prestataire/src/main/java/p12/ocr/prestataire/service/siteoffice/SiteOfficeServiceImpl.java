package p12.ocr.prestataire.service.siteoffice;

import p12.ocr.prestataire.model.SiteOffice;
import p12.ocr.prestataire.repository.ISiteOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteOfficeServiceImpl implements ISiteOfficeService {

    @Autowired
    private ISiteOfficeRepository siteOfficeRepository;


    /**
     * Permet la recherche d'un employé RH du site de l'entreprise prestataire
     * @param id Identifiant de l'employé RH recherché
     * @return SiteOffice si connu sinon null
     */
    public SiteOffice findById(Long id){ return siteOfficeRepository.findById(id).orElse(null);}

    /**
     * Permet la recherche de la liste des employés RHs su site de l'entreprise prestataire
     * @return List<SiteOffice>
     */
    public List<SiteOffice> findAll(){return siteOfficeRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d'un employé RH du site de l'entreprise prestataire
     * @param siteOffice SiteOffice : Employé à créer ou à modifier
     * @return SiteOffice
     */
    public SiteOffice save (SiteOffice siteOffice){
        SiteOffice site = siteOfficeRepository.save( siteOffice );
        return site;
    }

    /**
     * Permet la suppression d'un employé RH su site de l'entreprise prestataire
     * @param id Idenitifiant de l'employé à supprimer
     */
    public void delete(Long id){
        SiteOffice siteOffice = findById( id );
        siteOfficeRepository.delete( siteOffice );
    }
}
