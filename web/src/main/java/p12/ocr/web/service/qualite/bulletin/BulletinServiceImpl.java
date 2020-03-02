package p12.ocr.web.service.qualite.bulletin;


import p12.ocr.web.beans.qualite.BulletinBean;
import p12.ocr.web.proxies.IQualiteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletinServiceImpl implements IBulletinService {

    @Autowired
    private IQualiteProxy qualiteProxy;
    /**
     * Permet la recherche d'un bulletin d'information
     * @param id Identifiant du bulletin recherché
     * @return BulletinBean si connu sinon null
     */
    public BulletinBean findById(Long id){ return qualiteProxy.findBulletinById(id);}

    /**
     * Permet la recherche de la liste des bulletin d'informations
     * @return List<BulletinBean>
     */
    public List<BulletinBean> findAll(){return  qualiteProxy.findAllBulletin();}

    /**
     * Permet la création d'un bulletin d'information
     * @param bulletin BulletinBean à créer
     * @return BulletinBean
     */
    public BulletinBean save (BulletinBean bulletin){return qualiteProxy.saveBulletin(bulletin );}

    /**
     * Permet la création ou la mise à jour d'un bulletin d'information
     * @param bulletin BulletinBean  à mettre à jour
     */
    public void update (BulletinBean bulletin){ qualiteProxy.updateBulletin(bulletin );}
}
