package p12.ocr.web.controller.marche;

import p12.ocr.web.beans.users.OrganizationBean;
import p12.ocr.web.beans.users.UserEntrepriseBean;
import p12.ocr.web.beans.marche.MarcheBean;
import p12.ocr.web.beans.marche.TypeMarcheBean;
import p12.ocr.web.beans.prestataire.EntrepriseBean;
import p12.ocr.web.beans.prestataire.SitePrestataireBean;
import p12.ocr.web.service.users.organization.IOrganizationService;
import p12.ocr.web.service.users.userentreprise.IUserEntrepriseService;
import p12.ocr.web.service.marche.marche.IMarcheService;
import p12.ocr.web.service.marche.typemarche.ITypeMarcheService;
import p12.ocr.web.service.prestataire.entreprise.IEntrepriseService;
import p12.ocr.web.service.prestataire.siteprestataire.ISitePrestataireService;
import p12.ocr.web.technical.date.SimpleDate;
import p12.ocr.web.technical.error.Field;
import p12.ocr.web.technical.error.FieldError;
import p12.ocr.web.technical.function.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/marche")
public class MarcheController {

    @Autowired
    private IMarcheService marcheService;

    @ModelAttribute
    public List<MarcheBean> marcheBeanList(){return marcheService.findAll();}


    @ModelAttribute
    public MarcheBean marcheBean(){return new MarcheBean();}

    @Autowired
    private IEntrepriseService entrepriseService;

    @ModelAttribute
    public List<EntrepriseBean> entrepriseBeanList(){return entrepriseService.findAll(); }

    @Autowired
    private ISitePrestataireService sitePrestataireService;

    @ModelAttribute
    public List<SitePrestataireBean> sitePrestataireBeanList(){return sitePrestataireService.findAll();}

    @Autowired
    private IUserEntrepriseService userEntrepriseService;

    @ModelAttribute
    public List<UserEntrepriseBean> userEntrepriseBeanList(){return userEntrepriseService.findAll();}

    @Autowired
    private ITypeMarcheService typeMarcheService;

    @ModelAttribute
    public List<TypeMarcheBean> typeMarcheBeanList(){return typeMarcheService.findAll();}

    @Autowired
    private IOrganizationService organizationService;

    @ModelAttribute
    public List<OrganizationBean> organizationBeanList(){return  organizationService.findAll(); }

    @Autowired
    private SimpleDate simpleDate;

    @Autowired
    private Plugin plugin;

    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("name" ),
                new Field("numero" ),
                new Field("eTravaux" ),
                new Field("fournisseur" ),
                new Field("client" ),
                new Field("dateStart" ),
                new Field("dateEnd" ),
                new Field("dateClosed" ),
                new Field("idPrestataire" ),
                new Field("idSite"),
                new Field("idRef1" ),
                new Field("idRef2"),
                new Field("typeMarche.id"),
                new Field("idOrganization")

        );
        return fieldList;
    }

    private  BindingResult resultController(BindingResult r, MarcheBean m ){

        List<FieldError> fieldErrorList = Arrays.asList(
                new FieldError("idPrestataire",  "L'entreprise prestaire est obligatoire.", m.getIdPrestataire(),false ),
                new FieldError("idSite", "Le site de l'entreprise prestaire est obligatoire.",m.getIdSite(),false ),
                new FieldError("idRef1",  "Le référent principal est obligatoire.", m.getIdRef1(),false ),
                new FieldError("idRef2",  "L''appui au référent est obligatoire.", m.getIdRef2(), false),
                new FieldError("typeMarche.id","Le type de marché est obligatoire.",m.getTypeMarche().getId(), false),
                new FieldError("idOrganization","Le rattachement à un territoire est obligatoire.",m.getIdOrganization(), false ),
                new FieldError("idRef2","L''appui au référent doit être différent du référent principal.",m.getIdRef1() != null && m.getIdRef1() == m.getIdRef2(), true ),
                new FieldError("dateEnd","La date de fin doit être supérieure à la date de début.",!simpleDate.isDate1BeforeDate2( m.getDateStart(),m.getDateEnd() ), true ),
                new FieldError("dateClosed","La date de clôture doit être supérieure à la date de fin.",!simpleDate.isDate1BeforeDate2( m.getDateEnd(),m.getDateClosed() ), true )
        );
        return plugin.resultController( r,  fieldErrorList ) ;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        return "marche/dashboard";
    }



    @GetMapping("/administration")
    public String list(Model model){
        return "marche/marche/administration/index";
    }



    @GetMapping("/add")
    public String add(Model model){return "marche/marche/administration/add";}


    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid MarcheBean marcheBean, BindingResult result, Model model, HttpSession session){

       result =  resultController( result,  marcheBean ) ;


        if (result.hasErrors() )
            return "marche/marche/administration/add";

        marcheService.save( marcheBean );
        session.setAttribute("menuOrganization",marcheService.getMenuMarche() );
        return "redirect:/marche/administration?addSuccess";

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        MarcheBean marcheBean = marcheService.findById( id );
        if(marcheBean == null)
            return "marche/marche/administration/add";

        model.addAttribute(  marcheBean );
        return "marche/marche/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable Long id, @Valid MarcheBean marcheBean, BindingResult result, HttpSession session){

        result =  resultController( result,  marcheBean ) ;

        if (result.hasErrors())
            return "marche/marche/administration/update";

        marcheService.update( marcheBean );
        session.setAttribute("menuOrganization",marcheService.getMenuMarche() );
        return "redirect:/marche/administration?updateSuccess";

    }



}
