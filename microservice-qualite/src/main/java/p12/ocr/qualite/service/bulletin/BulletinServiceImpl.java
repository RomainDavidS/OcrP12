package p12.ocr.qualite.service.bulletin;

import p12.ocr.qualite.model.Bulletin;
import p12.ocr.qualite.repository.IBulletinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulletinServiceImpl implements IBulletinService {

    @Autowired
    private IBulletinRepository bulletinRepository;

    /**
     * Permet la recherche d'un bulletin d'information
     * @param id Identifiant du bulletin recherché
     * @return Bulletin si connu sinon null
     */
    public Bulletin findById(Long id){ return bulletinRepository.findById(id).orElse(null);}

    /**
     * Permet la recherche de la liste des bulletin d'informations
     * @return List<Bulletin>
     */
    public List<Bulletin> findAll(){return  bulletinRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d'un bulletin d'information
     * @param bulletin Bulletin à créer ou à mettre à jour
     * @return Bulletin
     */
    public Bulletin save (Bulletin bulletin){return bulletinRepository.save(bulletin );}
}
