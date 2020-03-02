package p12.ocr.web.technical.function;

import p12.ocr.web.beans.users.UserEntrepriseBean;
import p12.ocr.web.beans.prestataire.EntrepriseBean;
import p12.ocr.web.service.users.userentreprise.IUserEntrepriseService;
import p12.ocr.web.service.files.files.IFilesService;
import p12.ocr.web.service.prestataire.entreprise.IEntrepriseService;
import p12.ocr.web.technical.date.SimpleDate;
import p12.ocr.web.technical.error.FieldError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.List;

@Component
public class Plugin {
    @Autowired
    private SimpleDate simpleDate;

    @Autowired
    private IFilesService filesService;

    public String getDate(Date date){return simpleDate.getDate( date ); }

    public String getDateTime(Date date){return simpleDate.getDateTime( date ); }

    public String getFileName(String id,String name ) {
        if( id != "" )
            return filesService.findFileNameById( id );
        else
            return name;
    }

    @Autowired
    private IEntrepriseService entrepriseService;

    public String entrepriseName(Long id){
        if(id == null )
            return "";

        EntrepriseBean entrepriseBean = entrepriseService.findById( id );
        return entrepriseBean !=null ? entrepriseBean.getName() : "";
    }

    @Autowired
    private IUserEntrepriseService userEntrepriseService;

    public String userEntrepriseFullName(Long id){
        UserEntrepriseBean userEntrepriseBean = userEntrepriseService.findById( id );
        return userEntrepriseBean != null ? userEntrepriseBean.getFirstName() + ' ' + userEntrepriseBean.getLastName() + " (" + userEntrepriseBean.getNni() + ")" : "";
    }



    public BindingResult resultController(BindingResult r, List<FieldError> fieldErrorList ){

        for (FieldError f : fieldErrorList ) {

            boolean bool = f.isBool() ? (boolean ) f.getValue() :  f.getValue() == null ;

            if ( bool )
                r.rejectValue(f.getName(), null, f.getMessage() );
        }
        return  r;
    }

}
