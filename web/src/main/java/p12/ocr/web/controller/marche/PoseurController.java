package p12.ocr.web.controller.marche;

import p12.ocr.web.beans.marche.MarcheBean;
import p12.ocr.web.beans.prestataire.EmployeeBean;
import p12.ocr.web.beans.prestataire.EntrepriseBean;
import p12.ocr.web.beans.prestataire.FonctionPrestataireBean;
import p12.ocr.web.beans.prestataire.SitePrestataireBean;
import p12.ocr.web.service.marche.marche.IMarcheService;
import p12.ocr.web.service.prestataire.employee.IEmployeeService;
import p12.ocr.web.service.prestataire.entreprise.IEntrepriseService;
import p12.ocr.web.service.prestataire.fonctionprestataire.IFonctionPrestataireService;
import p12.ocr.web.service.prestataire.siteprestataire.ISitePrestataireService;
import p12.ocr.web.technical.buttonradio.InitButtonRadio;
import p12.ocr.web.technical.error.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/marche/info/poseur")
public class PoseurController {

    @Autowired
    private IEmployeeService employeeService;


    @Autowired
    private IMarcheService marcheService;

    @Autowired
    private ISitePrestataireService sitePrestataireService;

    @Autowired
    private IEntrepriseService entrepriseService;

    @Autowired
    private IFonctionPrestataireService fonctionPrestataireService;

    @ModelAttribute
    public EmployeeBean employeeBean(){
        EmployeeBean employeeBean = new EmployeeBean();
        employeeBean.setEnabled( true );
        employeeBean.setNewEmployee( false );
        return employeeBean;
    }

    @ModelAttribute
    public List<Field> fieldList (){
        List< Field > fieldList = Arrays.asList(
                new Field("nni"),
                new Field("firstName"),
                new Field("lastName"),
                new Field("startDateContract"),
                new Field("startDateConfidential"),
                new Field("startDateHabilitation"),
                new Field("startDateQualification"),
                new Field("startDateIpsItst")
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

    @ModelAttribute("tagIsNewPoseur")
    public List<InitButtonRadio> initIsAccountNoExpiredRadio(){
        List< InitButtonRadio > initButtonRadios = Arrays.asList(
                new InitButtonRadio(true,"Oui"),
                new InitButtonRadio(false,"Non")
        );
        return  initButtonRadios;
    }

    @GetMapping("/actif/{idMarche}")
    public String listActif(@PathVariable Long idMarche, Model model){


        MarcheBean marcheBean = marcheService.findById( idMarche );
        model.addAttribute("idMarche", idMarche );
        model.addAttribute("idSitePrestataire", marcheBean.getIdSite() );
        SitePrestataireBean sitePrestataireBean = sitePrestataireService.findById( marcheBean.getIdSite() );

        model.addAttribute("marcheName", marcheBean.getName() );
        model.addAttribute("siteName", sitePrestataireBean.getName() );
        model.addAttribute("titlePoseur", "Liste des poseurs actifs" );


        List<EmployeeBean> employeeBeanList = employeeService.findAllActif( marcheBean.getIdSite() );
        model.addAttribute( "employeeBeanList",employeeBeanList );
        return "marche/marche/info/poseur";
    }

    @GetMapping("/inactif/{idMarche}")
    public String listInactif(@PathVariable Long idMarche,Model model){

        MarcheBean marcheBean = marcheService.findById( idMarche );
        model.addAttribute("idMarche", idMarche );
        model.addAttribute("idSitePrestataire", marcheBean.getIdSite() );
        List<EmployeeBean> employeeBeanList = employeeService.findAllInactif( marcheBean.getIdSite() );
        model.addAttribute( "employeeBeanList",employeeBeanList );

        SitePrestataireBean sitePrestataireBean = sitePrestataireService.findById( marcheBean.getIdSite() );

        model.addAttribute("marcheName", marcheBean.getName() );
        model.addAttribute("siteName", sitePrestataireBean.getName() );
        model.addAttribute("titlePoseur", "Liste des poseurs inactifs" );

        return "marche/marche/info/poseur";
    }

    @GetMapping("/info/{id}/{idMarche}")
    public String info(@PathVariable Long id,@PathVariable Long idMarche,Model model){
        EmployeeBean employeeBean = employeeService.findById( id );


        MarcheBean marcheBean = marcheService.findById( idMarche );

        if(employeeBean == null || marcheBean == null){
            model.addAttribute("idMarche", idMarche );
            return "marche/marche/info/poseur/add";
        }

        SitePrestataireBean sitePrestataireBean = sitePrestataireService.findById( marcheBean.getIdSite() );

        model.addAttribute( sitePrestataireBean );
        model.addAttribute( employeeBean );
        model.addAttribute( marcheBean );
        model.addAttribute("idMarche", idMarche );

        return "marche/marche/info/poseur/info";
    }

    @GetMapping("/add/{idMarche}")
    public String add(@PathVariable Long idMarche,Model model){

        model.addAttribute("idMarche", idMarche );

        return "marche/marche/info/poseur/add";

    }

    @PostMapping("/save/{idMarche}")
    public String save(@PathVariable Long idMarche, @ModelAttribute @Valid EmployeeBean employeeBean, BindingResult result, Model model){

        model.addAttribute("idMarche", idMarche );

        if (result.hasErrors()){
            model.addAttribute("idMarche", idMarche );
            return "marche/marche/info/poseur/add";
        }

       EmployeeBean employee = employeeService.save( setEmployee( employeeBean, idMarche ) );
        return "redirect:/marche/info/poseur/info/" + employee.getId() + "/" + idMarche;
    }

    @GetMapping("/edit/{id}/{idMarche}")
    public String edit(@PathVariable Long id, @PathVariable Long idMarche,Model model){

        EmployeeBean employeeBean = employeeService.findById( id );
        if(employeeBean == null){
            model.addAttribute("idMarche", idMarche );
            return "marche/marche/info/poseur/add";
        }

        model.addAttribute( employeeBean );
        model.addAttribute("idMarche", idMarche );
        return "marche/marche/info/poseur/update";
    }

    @PostMapping("/update/{id}/{idMarche}")
    public String update(@PathVariable Long id,
                         @PathVariable Long idMarche,
                         @ModelAttribute @Valid EmployeeBean employeeBean,
                         BindingResult result, Model model){

        model.addAttribute("idMarche", idMarche );
        if (result.hasErrors()) {
            model.addAttribute("idMarche", idMarche );
            return "marche/marche/info/poseur/update";
        }


         employeeService.update( setEmployee( employeeBean, idMarche ) );
        return "redirect:/marche/info/poseur/info/" + id + "/" + idMarche;

    }

    @ResponseBody
    private EmployeeBean setEmployee(EmployeeBean employeeBean, Long idMarche ){

        FonctionPrestataireBean fonctionPrestataire = fonctionPrestataireService.findById( -1L );

        MarcheBean marche = marcheService.findById( idMarche );
        Long idEntreprise = marche.getIdSousTraitant() != null ? marche.getIdSousTraitant() : marche.getIdPrestataire() ;

        EntrepriseBean entreprise = entrepriseService.findById( idEntreprise );

        SitePrestataireBean sitePrestataire = sitePrestataireService.findById( marche.getIdSite() );
        employeeBean.setEntreprise( entreprise );
        employeeBean.setFonctionPrestataire( fonctionPrestataire );
        employeeBean.setSitePrestataire( sitePrestataire );
        return employeeBean;
    }
}
