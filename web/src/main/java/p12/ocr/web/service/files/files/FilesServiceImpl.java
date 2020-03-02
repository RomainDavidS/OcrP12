package p12.ocr.web.service.files.files;

import p12.ocr.web.beans.files.FilesBean;
import p12.ocr.web.exception.FileStorageException;
import p12.ocr.web.proxies.IFilesProxy;

import p12.ocr.web.technical.enums.typefile.TypeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FilesServiceImpl implements IFilesService {

    @Autowired
    private IFilesProxy filesProxy;

    /**
     * Permet la création de FilesBean et de sa sauvegarde en base de données
     * @param file Fichier à sauvegarder en base de données
     * @param typeFile Type de fichier à Sauvegarder
     * @return FilesBean
     */
    public FilesBean storeFile(MultipartFile file, TypeFile typeFile)  {
        FilesBean filesBean = new FilesBean( );

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Le chemin d'accès au fichier "+ fileName + " est invalide.");
            }

            filesBean.setFileName( fileName );
            filesBean.setFileType( file.getContentType() );
            filesBean.setData( file.getBytes() );
            filesBean.setSize( file.getSize() );

            filesBean.setTypeFile( typeFile );
            return save( filesBean );

        } catch (IOException ex) {

            throw new FileStorageException("Impossible de stocker ou de trouver  le fichier " + fileName + ". Veuillez réessayer!", ex);
        }
    }

    /**
     * Recherche d'un fichier
     * @param id Identifiant du fichier à rechercher
     * @return FilesBean
     */
    public FilesBean findById(String id ){return filesProxy.findFilesById( id ); }

    /**
     * Recherche du nom d'un fichier
     * @param id Identifiant du fichier à rechercher
     * @return Nom du fichier
     */
    public String findFileNameById(String id ){return filesProxy.findFileNameById( id ); }

    /**
     * Permet la recherche de la liste des fichiers à rechercher
     * @return List<FilesBean>
     */
    public List<FilesBean> findAll(){return filesProxy.findAllFiles();}

    /**
     * Permet la création d'un fichier
     * @param filesBean FilesBean à créer
     * @return FilesBean
     */
    public FilesBean save(FilesBean filesBean ){ return filesProxy.saveFiles( filesBean ); }

    /**
     * Permet la mise à jour d'un fichier
     * @param filesBean FilesBean à mettre à jour
     */
    public void update(FilesBean filesBean ){filesProxy.updateFiles( filesBean );}
}
