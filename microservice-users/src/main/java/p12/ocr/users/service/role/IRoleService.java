package p12.ocr.users.service.role;

import p12.ocr.users.model.Role;

import java.util.List;

public interface IRoleService {

    Role findById(Long id);
    List<Role> findAll();
}
