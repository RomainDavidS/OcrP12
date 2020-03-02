package p12.ocr.files.service.files;

import p12.ocr.files.model.Files;
import p12.ocr.files.repository.IFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesServiceImpl implements IFilesService {

    @Autowired
    private IFilesRepository filesRepository;

    /**
     * Permet la recherche d'un fichier
     * @param id Identifiant du fichier à rechercher
     * @return Files si connu sinon null
     */
    public Files findById(String id){return filesRepository.findById(id).orElse(null);}

    /**
     * Permet la recherche du nom d'un fichier
     * @param id Identifiant du nom du fichier à rechercher
     * @return Nom du fichier si connu sinon null
     */
    public String findFileNameById(String id){return filesRepository.findById(id).orElse(null).getFileName();}

    /**
     * Permet la recherche de la liste des fichiers
     * @return List<Files>
     */
    public List<Files> findAll(){ return filesRepository.findAll();}

    /**
     * Permet la création ou la mise à jour d'un fichier
     * @param files Files à créer ou à mettre à jour
     * @return Files
     */
    public Files save(Files files){return  filesRepository.save( files ); }
}
