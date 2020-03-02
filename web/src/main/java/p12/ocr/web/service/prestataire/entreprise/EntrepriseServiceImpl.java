package p12.ocr.web.service.prestataire.entreprise;


import p12.ocr.web.beans.prestataire.SiretEntrepriseBean;
import p12.ocr.web.technical.api.siret.ApiInsee;
import p12.ocr.web.beans.prestataire.EntrepriseBean;
import p12.ocr.web.proxies.IPrestataireProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

    @Autowired
    private IPrestataireProxy prestataireProxy;

    /**
     * Permet la recherche d'une entreprise prestataire
     * @param id Identifiant de l'enteprise recherchée
     * @return EntrepriseBean si connu sinon null
     */
    public EntrepriseBean findById(Long id) {return prestataireProxy.findEntrepriseById(id);}

    /**
     * Permet la recherche d'une entreprise prestataire
     * @param siret SIRET de l'entreprise recherchée
     * @return EntrepriseBean
     */
    public EntrepriseBean findBySiret(String siret){
        if(siret == "")
            return null;

        return  prestataireProxy.findEntrepriseBySiret( siret );
    }

    /**
     * Permet de vérifier si l'entreprise est déjà connue
     * @param entrepriseBean EntrepriseBean à vérifier
     * @return true si EntrepriseBean est connue sinon false
     */
    public boolean isExisting(EntrepriseBean entrepriseBean){
        EntrepriseBean entreprise = findBySiret( entrepriseBean.getSiret() );
        return entreprise == null ? false : !entreprise.getId().equals( entrepriseBean.getId() ) && entreprise.getSiret().equals( entrepriseBean.getSiret() );

    }

    /**
     * Permet la recherche de la liste des entreprises prestataires
     * @return List<EntrepriseBean> findAll()
     */
    public List<EntrepriseBean> findAll(){ return prestataireProxy.findAllEntreprise() ;}

    /**
     * Permet la création d'une entreprise
     * @param entreprise EntrepriseBean à créer
     * @return EntrepriseBean
     */
    public EntrepriseBean save(EntrepriseBean entreprise ){return prestataireProxy.saveEntreprise( entreprise );}

    /**
     * Permet de mettre à jour une entreprise
     * @param entreprise EntrepriseBean à mettre à jour
     */
    public void update(EntrepriseBean entreprise ){ prestataireProxy.updateEntreprise( entreprise );}

    /**
     * Permet de vérifier si l'entreprise est répertorié dans le registre national des entreprises française
     * Grace à une api externe
     * @param apiInsee API externe utilisé
     * @return SiretEntrepriseBean
     */
    public SiretEntrepriseBean searchSiret(ApiInsee apiInsee)  {
        SiretEntrepriseBean siretEntrepriseBean = new SiretEntrepriseBean();
        siretEntrepriseBean.setResponseCode( apiInsee.getResponseCode() );
        siretEntrepriseBean.setResponseLibelle( apiInsee.getResponseLibelle() );

        if (apiInsee.getResponseCode() == 200) {

            siretEntrepriseBean.setSiret(apiInsee.getSiretEntreprise().getEtablissement().getSiret());
            siretEntrepriseBean.setAdressPostaleComplement(apiInsee.getSiretEntreprise().getEtablissement().getAdresseEtablissement().getComplementAdresseEtablissement());
            siretEntrepriseBean.setAdressPostaleAdress(
                    apiInsee.getSiretEntreprise().getEtablissement().getAdresseEtablissement().getNumeroVoieEtablissement() + " "
                            + apiInsee.getSiretEntreprise().getEtablissement().getAdresseEtablissement().getTypeVoieEtablissement() + " "
                            + apiInsee.getSiretEntreprise().getEtablissement().getAdresseEtablissement().getLibelleVoieEtablissement()
            );
            siretEntrepriseBean.setAdressPostaleCommune(
                    apiInsee.getSiretEntreprise().getEtablissement().getAdresseEtablissement().getCodePostalEtablissement() + " "
                            + apiInsee.getSiretEntreprise().getEtablissement().getAdresseEtablissement().getLibelleCommuneEtablissement()
            );

            siretEntrepriseBean.setName(
                    apiInsee.getSiretEntreprise().getEtablissement().getUniteLegale().getDenominationUniteLegale()
            );
        }

        return siretEntrepriseBean;
    }
}
