package p12.ocr.prestataire.service.fonctionprestataire;

import p12.ocr.prestataire.model.FonctionPrestataire;
import p12.ocr.prestataire.repository.IFonctionPrestataireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FonctionPrestataireImpl implements IFonctionPrestataireService {

    @Autowired
    private IFonctionPrestataireRepository fonctionPrestataireRepository;

    /**
     * Permet la recherche d'une fonction de l'entreprise prestaire
     * @param id Identifiant de la fonction recherchée
     * @return FonctionPrestataire si connu sinon null
     */
    public FonctionPrestataire findById(Long id){return fonctionPrestataireRepository.findById(id).orElse(null);}

    /**
     * Permet la recherche de la liste des fonctions de l'entreprise prestataire
     * @return List<FonctionPrestataire>
     */
    public List<FonctionPrestataire> findAll(){return  fonctionPrestataireRepository.findAll();}

    /**
     * Permeet la création ou la mise à jour d'une fonction de l'entreprise prestataire
     * @param fonctionPrestataire FonctionPrestataire à créer ou à mettre à jour
     * @return FonctionPrestataire
     */
    public FonctionPrestataire save(FonctionPrestataire fonctionPrestataire){return fonctionPrestataireRepository.save( fonctionPrestataire);}



}
