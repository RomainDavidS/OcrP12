package p12.ocr.web.service.users.privilege;

import p12.ocr.web.beans.users.PrivilegeBean;

import java.util.List;

public interface IPrivilegeService {

    PrivilegeBean findByName(String name);
    List<PrivilegeBean> findAll();
}
