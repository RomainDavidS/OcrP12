package p12.ocr.users.service.privilege;

import p12.ocr.users.model.Privilege;

import java.util.List;

public interface IPrivilegeService {

    Privilege findByName(String name);
    List<Privilege> findAll();
}
