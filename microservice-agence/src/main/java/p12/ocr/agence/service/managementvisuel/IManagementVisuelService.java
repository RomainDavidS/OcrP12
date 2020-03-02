package p12.ocr.agence.service.managementvisuel;

import p12.ocr.agence.model.ManagementVisuel;

import java.util.List;

public interface IManagementVisuelService {

    ManagementVisuel findById(String id);
    List<ManagementVisuel> findAll();
    ManagementVisuel save(ManagementVisuel managementVisuel);
}
