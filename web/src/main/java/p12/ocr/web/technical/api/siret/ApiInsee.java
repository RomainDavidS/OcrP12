package p12.ocr.web.technical.api.siret;

import com.fasterxml.jackson.databind.ObjectMapper;
import p12.ocr.web.config.ApiInseeConfig;
import p12.ocr.web.technical.ssl.SslContext;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


/**
 * #API insee : https://api.insee.fr/
 */
@Component
@Getter
public class ApiInsee {

    @Autowired
    private SslContext sslContext;

    @Autowired
    private ApiInseeConfig apiInseeConfig;

    private  SiretEntreprise siretEntreprise;

    private int responseCode;

    private String responseLibelle;

    public void searchSiret(String siret) throws NoSuchAlgorithmException, KeyManagementException {
        siretEntreprise = null;
        ObjectMapper mapper = new ObjectMapper();
        sslContext.setSslContext();


        try {
            URL url = new URL(apiInseeConfig.getUrl() + "siret/" + siret);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept","application/json");
            conn.setRequestProperty("Authorization","Bearer " + apiInseeConfig.getKey() );
            responseCode = conn.getResponseCode();
            responseLibelle = getResponse( responseCode );
            if (responseCode == 200)
                siretEntreprise = mapper.readValue(conn.getInputStream(), SiretEntreprise.class);

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getResponse( int code){

        switch (code ){
            case  200:
                return  "OK";
            case 301:
                return  "Établissement d'une unité légale fermée pour cause de doublon";
            case 400:
                return "Nombre incorrect de paramètres ou les paramètres sont mal formatés";
            case 401:
                return "Jeton d'accès manquant ou invalide";
            case 403 :
                return "Droits insuffisants pour consulter les données de cette unité";
            case 404:
                return "Entreprise non trouvée";
            case 406:
                return "Quota d'interrogations de l'API dépassé";
            case 500:
                return "Erreur interne du serveur";
            case 503 :
                return "Service indisponible";
            default:
                return "Erreur non connue.";
        }

    }
}
