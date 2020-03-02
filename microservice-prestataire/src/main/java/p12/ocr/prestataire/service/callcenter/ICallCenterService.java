package p12.ocr.prestataire.service.callcenter;

import p12.ocr.prestataire.model.CallCenter;

import java.util.List;

public interface ICallCenterService {

    CallCenter findById(Long id);
    List<CallCenter> findAll();
    CallCenter save(CallCenter callCenter );
}
