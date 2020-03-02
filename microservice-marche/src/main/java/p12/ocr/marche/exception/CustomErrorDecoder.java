package p12.ocr.marche.exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {

        if(response.status() == 400 ) {
            return new ResourceBadRequestException("Requête incorrecte.");
        }else if (response.status() == 404 ) {
            return new ResourceNotFoundException( "Ressource non trouvé.");
        }
        return defaultErrorDecoder.decode(s, response);
    }
}
