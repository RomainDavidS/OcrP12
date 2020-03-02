package p12.ocr.web.controller.prestataire;

import p12.ocr.web.beans.prestataire.EntrepriseBean;
import p12.ocr.web.beans.prestataire.SiretEntrepriseBean;
import p12.ocr.web.service.prestataire.entreprise.IEntrepriseService;
import p12.ocr.web.technical.api.siret.ApiInsee;
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
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/entreprise")
public class EntrepriseController {

    @Autowired
    private IEntrepriseService entrepriseService;

    @Autowired
    private ApiInsee apiInsee;

    @ModelAttribute
    public EntrepriseBean entrepriseBean(){
        EntrepriseBean entrepriseBean  = new EntrepriseBean();
        entrepriseBean.setEnabled( true );
        return entrepriseBean;}

    @ModelAttribute
    public List<EntrepriseBean> entrepriseBeanList(){return entrepriseService.findAll();}



    @Autowired
    private SimpleDate simpleDate;

    @Autowired
    private Plugin plugin;

    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("name"),
                new Field("siret"),
                new Field("adressPostaleComplement"),
                new Field("adressPostaleAdress"),
                new Field("adressPostaleCommune")
        );
        return fieldList;
    }

    private BindingResult resultController(BindingResult r, EntrepriseBean e ){

        List<FieldError> fieldErrorList = Arrays.asList(
              //  new FieldError("idPrestataire",  "L'entreprise prestaire est obligatoire.", m.getIdPrestataire(),false )

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
        return "prestataire/entreprise/administration/index";
    }

    @GetMapping("/add")
    public String add(Model model){return "prestataire/entreprise/administration/add";}


    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid EntrepriseBean entrepriseBean, BindingResult result, Model model){

        result =  resultController( result,  entrepriseBean ) ;

        if( entrepriseService.isExisting( entrepriseBean ) )
            result.rejectValue("siret",null,"Le numéro siret de l'entreprise existe déjà.");

        if (result.hasErrors() )
            return "prestataire/entreprise/administration/add";

        entrepriseService.save( entrepriseBean );

        return "redirect:/entreprise/administration?addSuccess";

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        EntrepriseBean entrepriseBean = entrepriseService.findById( id );
        if(entrepriseBean ==null)
            return "prestataire/entreprise/administration/add";

        model.addAttribute(  entrepriseBean );
        return "prestataire/entreprise/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable Long id, @Valid EntrepriseBean entrepriseBean, BindingResult result){

        result =  resultController( result,  entrepriseBean ) ;

        if( entrepriseService.isExisting( entrepriseBean ) )
            result.rejectValue("siret",null,"Le numéro siret de l''entreprise existe déjà.");

        if (result.hasErrors())
            return "prestataire/entreprise/administration/update";

        entrepriseService.update( entrepriseBean );
        return "redirect:/entreprise/administration?updateSuccess";

    }


    @PostMapping("/searchSiret")
    @ResponseBody
    public SiretEntrepriseBean searchSiret (@RequestParam("siret") String siret) throws  KeyManagementException, NoSuchAlgorithmException {

        apiInsee.searchSiret( siret );
        return entrepriseService.searchSiret(  apiInsee );

    }

}
