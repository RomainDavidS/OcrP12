package p12.ocr.users.service.organization;

import p12.ocr.users.model.Organization;

import java.util.List;

public interface IOrganizationService {

    Organization findById(Long id);
    List<Organization> findAll();
    Organization save(Organization organization );
}
