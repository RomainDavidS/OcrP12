package p12.ocr.web.service.prestataire.callcenter;


import p12.ocr.web.beans.prestataire.CallCenterBean;
import p12.ocr.web.proxies.IPrestataireProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallCenterServiceImpl implements ICallCenterService {

    @Autowired
    private IPrestataireProxy prestataireProxy;

    /**
     * Permet la recherche d'un call center des entreprise prestataires
     * @param id Identifiant du call center recherché
     * @return CallCenterBean si connu sinon null
     */
    public CallCenterBean findById(Long id) {return prestataireProxy.findCallCenterById(id);}

    /**
     * Permet la recherche de la liste des calls center des entreprises prestataire
     * @return List<CallCenterBean>
     */
    public List<CallCenterBean> findAll(){ return prestataireProxy.findAllCallcenter() ;}

    /**
     * Permet la création d'un call center des entreprises prestataire
     * @param callCenter CallCenterBean à créer
     * @return CallCenterBean
     */
    public CallCenterBean save(CallCenterBean callCenter ){return prestataireProxy.saveCallCenter( callCenter );}

    /**
     * Permet la mise à jour d'un call center des entreprises prestataires
     * @param callCenter CallCenterBean à mettre à jour
     */
    public void update(CallCenterBean callCenter ){ prestataireProxy.updateCallCenter( callCenter );}
}
