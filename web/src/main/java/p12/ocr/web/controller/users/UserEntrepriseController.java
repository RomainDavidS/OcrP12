package p12.ocr.web.controller.users;

import p12.ocr.web.beans.users.FonctionBean;
import p12.ocr.web.beans.users.OrganizationBean;
import p12.ocr.web.beans.users.RoleBean;
import p12.ocr.web.beans.users.SiteEntrepriseBean;
import p12.ocr.web.beans.users.UserEntrepriseBean;
import p12.ocr.web.service.users.fonction.IFonctionService;
import p12.ocr.web.service.users.organization.IOrganizationService;
import p12.ocr.web.service.users.role.IRoleService;
import p12.ocr.web.service.users.site.ISiteEntrepriseService;
import p12.ocr.web.service.users.userentreprise.IUserEntrepriseService;
import p12.ocr.web.technical.buttonradio.InitButtonRadio;
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
@RequestMapping("/entreprise/user")
public class UserEntrepriseController {

    @Autowired
    private IUserEntrepriseService userEntrepriseService;

    @ModelAttribute
    public List<UserEntrepriseBean> userEntrepriseBeanList(){return  userEntrepriseService.findAll();}

    @Autowired
    private ISiteEntrepriseService siteEntrepriseService;

    @ModelAttribute
    public List<SiteEntrepriseBean> siteEntrepriseBeanList(){return siteEntrepriseService.findAll();}

    @Autowired
    private IFonctionService fonctionService;

    @ModelAttribute
    public List<FonctionBean> fonctionBeanList(){return fonctionService.findAll();}

    @Autowired
    private IRoleService roleService;

    @ModelAttribute
    public List<RoleBean> roleBeanList(){return roleService.findAll();}

    @Autowired
    private IOrganizationService organizationService;

    @ModelAttribute
    public List<OrganizationBean> organizationBeanList(){return organizationService.findAll();}

    @ModelAttribute
    public UserEntrepriseBean userEntrepriseBean(){
        UserEntrepriseBean userEntrepriseBean = new UserEntrepriseBean();
        userEntrepriseBean.setAccountNoExpired(true);
        userEntrepriseBean.setEnabled(true);
        userEntrepriseBean.setAccountNoLocked(true);
        userEntrepriseBean.setTokenNoExpired(true);
        return userEntrepriseBean; }


    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("nni" ),
                new Field("firstName" ),
                new Field("lastName" ),
                new Field("email" ),
                new Field("role.id" ),
                new Field("organization.id" ),
                new Field("fonction.id" ),
                new Field("site.id" )
        );
        return fieldList;
    }

    @ModelAttribute("tagIsEnabled")
    public List<InitButtonRadio> initIsEnabledRadio(){
        List< InitButtonRadio > initButtonRadios = Arrays.asList(
                new InitButtonRadio(true,"Actif"),
                new InitButtonRadio(false,"Inactif")

        );

        return  initButtonRadios;
    }
    @ModelAttribute("tagIsAccountNoExpired")
    public List<InitButtonRadio> initIsAccountNoExpiredRadio(){
        List< InitButtonRadio > initButtonRadios = Arrays.asList(
                new InitButtonRadio(true,"Compte non expiré"),
                new InitButtonRadio(false,"Compte Expiré")

        );

        return  initButtonRadios;
    }

    @ModelAttribute("tagIsAccountNoLocked")
    public List<InitButtonRadio> initIsAccountNoLockedRadio(){
        List< InitButtonRadio > initButtonRadios = Arrays.asList(
                new InitButtonRadio(true,"Compte non bloqué"),
                new InitButtonRadio(false,"Compte bloqué")

        );

        return  initButtonRadios;
    }
    @ModelAttribute("tagIsTokenNoExpired")
    public List<InitButtonRadio> initIsTokenNoExpiredRadio(){
        List< InitButtonRadio > initButtonRadios = Arrays.asList(
                new InitButtonRadio(true,"Token non expiré"),
                new InitButtonRadio(false,"Token expiré")

        );

        return  initButtonRadios;
    }

    @Autowired
    private Plugin plugin;

    private  BindingResult resultController(BindingResult r, UserEntrepriseBean u ){

        List<FieldError> fieldErrorList = Arrays.asList(
                new FieldError("role.id",  "Vous devez sélectionner un rôle.", u.getRole().getId(),false ),
                new FieldError("fonction.id", "Vous devez sélectionner une fonction.",u.getFonction().getId(),false ),
                new FieldError("organization.id",  "Vous devez sélectionner une organisation.", u.getOrganization().getId(),false ),
                new FieldError("site.id",  "Vous devez sélectionner un site.", u.getSite().getId(), false)
        );
        return plugin.resultController( r,  fieldErrorList ) ;
    }

    @GetMapping("/administration")
    public String list(Model model){

        return "entreprise/user/administration/index";

    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("readonly",false);
        return "entreprise/user/administration/add"; }

    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid UserEntrepriseBean userEntrepriseBean, BindingResult result, Model model){


        UserEntrepriseBean existing = userEntrepriseService.findByNni(userEntrepriseBean.getNni() );
        if (existing != null)
            result.rejectValue("nni", null, "Il y a déjà un compte enregistré avec ce nni.");

        result =  resultController( result, userEntrepriseBean) ;


        if (result.hasErrors()) {
            model.addAttribute("readonly",false);
            return "entreprise/user/administration/add";
        }

        userEntrepriseService.save(userEntrepriseBean);

        return "redirect:/entreprise/user/administration?addSuccess";

    }

    @GetMapping("/edit/{nni}")
    public String edit(@PathVariable String nni, Model model) {
        UserEntrepriseBean userEntrepriseBean = userEntrepriseService.findByNni( nni );
        if(userEntrepriseBean == null){
            model.addAttribute("readonly",false);
            return "entreprise/user/administration/add";
        }

        model.addAttribute(userEntrepriseBean);
        model.addAttribute("readonly",true);
        return "entreprise/user/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid UserEntrepriseBean userEntrepriseBean, BindingResult result, Model model){

        result =  resultController( result, userEntrepriseBean) ;

        if (result.hasErrors()) {
            model.addAttribute("readonly",true);
            return "entreprise/user/administration/update";
        }
        UserEntrepriseBean userEntreprise = userEntrepriseService.findById( id );
        userEntrepriseBean.setLastConnection( userEntreprise .getLastConnection() );
        userEntrepriseBean.setDateCreate( userEntreprise.getDateCreate() );
        userEntrepriseService.update(userEntrepriseBean);
        return "redirect:/entreprise/user/administration?updateSuccess";

    }
}
