package p12.ocr.web.controller.prestataire;

import p12.ocr.web.beans.prestataire.CallCenterBean;
import p12.ocr.web.beans.prestataire.EntrepriseBean;
import p12.ocr.web.beans.prestataire.SitePrestataireBean;
import p12.ocr.web.service.prestataire.callcenter.ICallCenterService;
import p12.ocr.web.service.prestataire.entreprise.IEntrepriseService;
import p12.ocr.web.service.prestataire.siteprestataire.ISitePrestataireService;
import p12.ocr.web.technical.buttonradio.InitButtonRadio;
import p12.ocr.web.technical.date.SimpleDate;
import p12.ocr.web.technical.error.Field;
import p12.ocr.web.technical.error.FieldError;
import p12.ocr.web.technical.function.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/sitePrestataire")
public class SitePrestataireController {

    @Autowired
    private ISitePrestataireService sitePrestataireService;


    @ModelAttribute
    public SitePrestataireBean sitePrestataireBean(){
        SitePrestataireBean sitePrestataireBean = new SitePrestataireBean() ;
        sitePrestataireBean.setEnabled( true );
        return sitePrestataireBean ;
    }

    @ModelAttribute
    public List<SitePrestataireBean> sitePrestataireBeanList(){return sitePrestataireService.findAll();}

    @Autowired
    private IEntrepriseService entrepriseService;

    @ModelAttribute
    public List<EntrepriseBean> entrepriseBeanList(){return entrepriseService.findAll();}

    @Autowired
    private ICallCenterService callCenterService;

    @ModelAttribute
    public List<CallCenterBean> callCenterBeanList(){ return callCenterService.findAll();}

    @Autowired
    private SimpleDate simpleDate;

    @Autowired
    private Plugin plugin;

    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("name"),
                new Field("codeZs"),
                new Field("adressLocalAdress"),
                new Field("adressLocalCommune"),
                new Field("adressPostaleAdress"),
                new Field("adressPostaleCommune"),
                new Field("entreprise.id"),
                new Field("callCenter.id")
        );
        return fieldList;
    }

    private BindingResult resultController(BindingResult r, SitePrestataireBean s ){

        List<FieldError> fieldErrorList = Arrays.asList(
                new FieldError("entreprise.id",  "L'entreprise prestataire est obligatoire.", s.getEntreprise().getId(),false ),
                new FieldError("callCenter.id",  "Le call center est obligatoire.", s.getCallCenter().getId(),false )

        );
        return plugin.resultController( r,  fieldErrorList ) ;
    }

    @ModelAttribute("tagIsEnabled")
    public List<InitButtonRadio> initIsEnabledRadio(){
        List< InitButtonRadio > initButtonRadios = Arrays.asList(
                new InitButtonRadio(true,"Actif"),
                new InitButtonRadio(false,"Inactif")

        );

        return  initButtonRadios;
    }

    @GetMapping("/administration")
    public String list(Model model){
        return "prestataire/siteprestataire/administration/index";
    }

    @GetMapping("/add")
    public String add(Model model){ return "prestataire/siteprestataire/administration/add";}


    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid SitePrestataireBean sitePrestataireBean, BindingResult result, Model model){

        result =  resultController( result,  sitePrestataireBean ) ;


        if (result.hasErrors() )
            return "prestataire/siteprestataire/administration/add";

        sitePrestataireService.save( sitePrestataireBean );

        return "redirect:/sitePrestataire/administration?addSuccess";

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        SitePrestataireBean sitePrestataireBean = sitePrestataireService.findById( id );
        if( sitePrestataireBean == null )
            return "prestataire/siteprestataire/administration/add";

        model.addAttribute(  sitePrestataireBean );
        return "prestataire/siteprestataire/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable Long id, @Valid SitePrestataireBean sitePrestataireBean, BindingResult result){

        result =  resultController( result,  sitePrestataireBean ) ;

        if (result.hasErrors())
            return "prestataire/siteprestataire/administration/update";

        sitePrestataireService.update( sitePrestataireBean );
        return "redirect:/sitePrestataire/administration?updateSuccess";

    }


}
