package p12.ocr.prestataire.exception;

/**
 * Exception lié au téléchargement d'un fichier
 */
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
