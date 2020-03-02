package p12.ocr.web.service.qualite.portage;

import p12.ocr.web.beans.qualite.PortageBean;

import java.util.List;

public interface IPortageService {

    PortageBean findById(Long id);
    List<PortageBean> findAll();
    PortageBean save(PortageBean portage);
    void update(PortageBean portage);

}
