package p12.ocr.web.service.qualite.portage;


import p12.ocr.web.beans.qualite.PortageBean;
import p12.ocr.web.proxies.IQualiteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortageServiceImpl implements IPortageService {

    @Autowired
    private IQualiteProxy qualiteProxy;

    /**
     * Permet la recherche du portage (date) d'un bulletin d'information à un marché
     * @param id Identifiant du portage recherché
     * @return PortageBean si connu sinon null
     */
    public PortageBean findById(Long id){ return qualiteProxy.findPortageById(id);}

    /**
     * Permet la recherche de la liste des portages (date) des bulletins d'information aux marchés
     * @return List<PortageBean>
     */
    public List<PortageBean> findAll(){return  qualiteProxy.findAllPortage();}

    /**
     * Permet la création  d'un portage (date) d'un bulletin d'information à un marché
     * @param portage PortageBean à créer
     * @return PortageBean
     */
    public PortageBean save (PortageBean portage){return qualiteProxy.savePortage( portage );}

    /**
     * Permet  la mise à jour d'un portage (date) d'un bulletin d'information à un marché
     * @param portage PortageBean à mettre à jour
     */
    public void update (PortageBean portage){ qualiteProxy.updatePortage( portage );}
}
