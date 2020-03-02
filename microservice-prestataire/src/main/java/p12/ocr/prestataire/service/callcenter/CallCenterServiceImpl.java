package p12.ocr.prestataire.service.callcenter;

import p12.ocr.prestataire.model.CallCenter;
import p12.ocr.prestataire.repository.ICallCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallCenterServiceImpl implements ICallCenterService {

    @Autowired
    private ICallCenterRepository callCenterRepository;

    /**
     * Permet la recherche d'un CallCenter
     * @param id Identifiant du call center recherché
     * @return CallCenter si connu sinon null
     */
    public CallCenter findById(Long id) {return callCenterRepository.findById(id).orElse(null);}

    /**
     * Permet la recherche de la liste des Calls center
     * @return List<CallCenter>
     */
    public List<CallCenter> findAll(){ return callCenterRepository.findAll() ;}

    /**
     * Permet la création ou la mise à jour d'un Call Center
     * @param callCenter CallCenter à créer ou à mette à jour
     * @return CallCenter
     */
    public CallCenter save(CallCenter callCenter ){return callCenterRepository.save( callCenter );}


}
