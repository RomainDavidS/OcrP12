package p12.ocr.web.service.qualite.marchequalite;

import p12.ocr.web.beans.qualite.MarcheQualiteBean;
import p12.ocr.web.proxies.IQualiteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcheQualiteServiceImpl implements IMarcheQualiteService {

    @Autowired
    private IQualiteProxy qualiteProxy;

    /**
     * Permet la recherche d'un marché abonné aux bulletins d'information
     * @param id Identifiant du marché recherché
     * @return MarcheQualiteBean si connu sinon null
     */
    public MarcheQualiteBean findById(Long id){ return qualiteProxy.findMarcheById(id);}

    /**
     * Permet la recherche de la liste des marchés abonnés aux bulletins d'information
     * @return List<MarcheQualiteBean>
     */
    public List<MarcheQualiteBean> findAll(){return  qualiteProxy.findAllMarche();}

    /**
     * Permet la création  d'un marché abonné aux bulletins d'informations
     * @param marche MarcheQualite à créer
     * @return MarcheQualite
     */
    public MarcheQualiteBean save (MarcheQualiteBean marche){return qualiteProxy.saveMarche(marche );}

    /**
     * Permet la mise à jour d'un marché abonné aux bulletins d'informations
     * @param marche MarcheQualiteBean ou à mettre à jour
     */
    public void update (MarcheQualiteBean marche){ qualiteProxy.updateMarche(marche );}
}
