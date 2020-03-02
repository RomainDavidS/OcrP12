package p12.ocr.web.controller.users;

import p12.ocr.web.beans.users.SiteEntrepriseBean;
import p12.ocr.web.beans.users.TypeSiteBean;
import p12.ocr.web.service.users.site.ISiteEntrepriseService;
import p12.ocr.web.service.users.typesite.ITypeSiteService;
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
@RequestMapping("/users/site")
public class SiteEntrepriseController {
    @Autowired
    private ISiteEntrepriseService siteEntrepriseService;

    @Autowired
    private ITypeSiteService typeSiteService;

    @ModelAttribute
    public List<SiteEntrepriseBean> siteEntrepriseBeanList(){return  siteEntrepriseService.findAll();}

    @ModelAttribute
    public List<TypeSiteBean> typeSiteBeanList(){return typeSiteService.findAll(); }

    @ModelAttribute
    public SiteEntrepriseBean siteEntrepriseBean(){return new SiteEntrepriseBean();}

    @Autowired
    private Plugin plugin;

    private  BindingResult resultController(BindingResult r, SiteEntrepriseBean s ){

        List<FieldError> fieldErrorList = Arrays.asList(
                new FieldError("typeSite.id",  "Vous devez sélectionner un type de site.",s.getTypeSite().getId(),false )
        );
        return plugin.resultController( r,  fieldErrorList ) ;
    }


    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("name" ),
                new Field("typeSite.id" )
        );
        return fieldList;
    }

    @GetMapping("/administration")
    public String list(Model model){

        return "users/site/administration/index";

    }

    @GetMapping("/add")
    public String add(Model model){return "users/site/administration/add"; }

    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid SiteEntrepriseBean siteEntrepriseBean, BindingResult result, Model model){

        result =  resultController( result, siteEntrepriseBean) ;

        if (result.hasErrors())
            return "users/site/administration/add";

        siteEntrepriseService.save(siteEntrepriseBean);

        return "redirect:/users/site/administration?addSuccess";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        SiteEntrepriseBean siteEntrepriseBean = siteEntrepriseService.findById( id );
        if( siteEntrepriseBean == null)
            return "users/site/administration/add";

        model.addAttribute(siteEntrepriseBean);
        return "users/site/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid SiteEntrepriseBean siteEntrepriseBean, BindingResult result){

        result =  resultController( result, siteEntrepriseBean) ;

        if (result.hasErrors())
            return "users/site/administration/update";

        siteEntrepriseService.update(siteEntrepriseBean);
        return "redirect:/users/site/administration?updateSuccess";

    }

}
