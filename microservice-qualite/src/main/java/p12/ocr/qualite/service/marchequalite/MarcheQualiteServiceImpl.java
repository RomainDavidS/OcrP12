package p12.ocr.qualite.service.marchequalite;

import p12.ocr.qualite.model.MarcheQualite;
import p12.ocr.qualite.repository.IMarcheQualiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcheQualiteServiceImpl implements IMarcheQualiteService {

    @Autowired
    private IMarcheQualiteRepository marcheBulletinRepository;

    /**
     * Permet la recherche d'un marché abonné aux bulletins d'information
     * @param id Identifiant du marché recherché
     * @return MarcheQualite si connu sinon null
     */
    public MarcheQualite findById(Long id){ return marcheBulletinRepository.findById(id).orElse(null);}

    /**
     * Permet la recherche de la liste des marchés abonnés aux bulletins d'information
     * @return List<MarcheQualite>
     */
    public List<MarcheQualite> findAll(){return  marcheBulletinRepository.findAll(Sort.by( Sort.Direction.ASC, "name"));}

    /**
     * Permet la création ou la mise à jour d'un marché abonné aux bulletins d'informations
     * @param marcheQualite MarcheQualite à créer ou à mettre à jour
     * @return MarcheQualite
     */
    public MarcheQualite save (MarcheQualite marcheQualite){return marcheBulletinRepository.save(marcheQualite);}
}
