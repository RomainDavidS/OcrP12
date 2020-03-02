package p12.ocr.web.service.users.organization;

import p12.ocr.web.beans.users.OrganizationBean;

import java.util.List;

public interface IOrganizationService {

    OrganizationBean findById(Long id);
    List<OrganizationBean> findAll();
    OrganizationBean save( OrganizationBean organization);
    void update( OrganizationBean organization);
}
