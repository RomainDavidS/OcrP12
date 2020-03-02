package p12.ocr.web.service.prestataire.callcenter;



import p12.ocr.web.beans.prestataire.CallCenterBean;

import java.util.List;

public interface ICallCenterService {

    CallCenterBean findById(Long id);
    List<CallCenterBean> findAll();
    CallCenterBean save(CallCenterBean callCenter);
    void update(CallCenterBean callCenter);
}
