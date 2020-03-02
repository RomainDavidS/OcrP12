package p12.ocr.web.service.agence.managementvisuel;


import p12.ocr.web.beans.agence.ManagementVisuelBean;

import java.util.List;

public interface IManagementVisuelService {

    ManagementVisuelBean findById(String id);
    List<ManagementVisuelBean> findAll();
    ManagementVisuelBean save(ManagementVisuelBean managementVisuel);
    void update(ManagementVisuelBean managementVisuel);
}
