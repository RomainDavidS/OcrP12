package p12.ocr.web.service.users.role;

import p12.ocr.web.beans.users.RoleBean;

import java.util.List;

public interface IRoleService {

    RoleBean findById(Long id);
    List<RoleBean> findAll();
}
