package p12.ocr.web.service.marche.typemarche;

import p12.ocr.web.beans.marche.TypeMarcheBean;
import p12.ocr.web.proxies.IMarcheProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TypeMarcheServiceImpl implements ITypeMarcheService {
    @Autowired
    private IMarcheProxy marcheProxy;

    /**
     * Permet la recherche du type de marché
     * @param id Identifiant du type de marché
     * @return TypeMarcheBean si connu sinon nul
     */
    public TypeMarcheBean findById( Long id){return marcheProxy.findTypeMarcheById( id );}

    /**
     * Permet la recherche de la liste des types de marché
     * @return List<TypeMarcheBean>
     */
    public List<TypeMarcheBean> findAll(){ return marcheProxy.findAllTypeMarche();}

    /**
     * Permet la création d'un type de marché
     * @param typeMarche TypeMarcheBean à créer
     * @return TypeMarcheBean
     */
    public TypeMarcheBean save( TypeMarcheBean typeMarche){return marcheProxy.saveTypeMarche( typeMarche ); }

    /**
     * Permet la mise à jour d'un type de marché
     * @param typeMarche TypeMarcheBean à mettre à jour
     */
    public void update( TypeMarcheBean typeMarche){marcheProxy.updateTypeMarche( typeMarche ); }
}
