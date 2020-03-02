package p12.ocr.web.service.qualite.bulletin;


import p12.ocr.web.beans.qualite.BulletinBean;

import java.util.List;

public interface IBulletinService {

    BulletinBean findById(Long id);
    List<BulletinBean> findAll();
    BulletinBean save(BulletinBean bulletin);
    void update(BulletinBean bulletin);
}
