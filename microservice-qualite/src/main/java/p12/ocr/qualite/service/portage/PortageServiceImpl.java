package p12.ocr.qualite.service.portage;

import p12.ocr.qualite.model.Portage;
import p12.ocr.qualite.repository.IPortageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortageServiceImpl implements IPortageService {

    @Autowired
    private IPortageRepository portageRepository;

    /**
     * Permet la recherche du portage (date) d'un bulletin d'information à un marché
     * @param id Identifiant du portage recherché
     * @return Portage si connu sinon null
     */
    public Portage findById(Long id){ return portageRepository.findById(id).orElse(null);}

    /**
     * Permet la recherche de la liste des portages (date) des bulletins d'information aux marchés
     * @return List<Portage>
     */
    public List<Portage> findAll(){return  portageRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d'un portage (date) d'un bulletin d'information à un marché
     * @param portage Portage à créer ou à mettre à jour
     * @return Portage
     */
    public Portage save (Portage portage){return portageRepository.save( portage );}
}
