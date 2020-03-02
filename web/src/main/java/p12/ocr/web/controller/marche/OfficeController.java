package p12.ocr.web.controller.marche;

import p12.ocr.web.beans.prestataire.SiteOfficeBean;
import p12.ocr.web.beans.prestataire.SitePrestataireBean;
import p12.ocr.web.service.prestataire.siteprestataire.ISitePrestataireService;
import p12.ocr.web.service.prestataire.siteoffice.ISiteOfficeService;
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
@RequestMapping("/marche/info/office")
public class OfficeController {


    @Autowired
    private ISitePrestataireService sitePrestataireService;

    @Autowired
    private ISiteOfficeService siteOfficeService;

    @ModelAttribute
    public SiteOfficeBean siteOfficeBean(){ return new SiteOfficeBean();}

    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("fonction" ),
                new Field("rhName" ),
                new Field("officePhone" ),
                new Field("email" )
        );
        return fieldList;
    }



    @GetMapping("/add/{idMarche}/{idSitePrestataire}")
    public String add(@PathVariable Long idMarche,
                      @PathVariable Long idSitePrestataire,
                      Model model){

        model.addAttribute("idMarche", idMarche );
        model.addAttribute("idSitePrestataire", idSitePrestataire );
        return "marche/marche/info/office/add";
    }

    @PostMapping("/save/{idMarche}/{idSitePrestataire}")
    public String save( @PathVariable Long idMarche,
                        @PathVariable Long idSitePrestataire,
                       @ModelAttribute @Valid SiteOfficeBean siteOfficeBean,
                       BindingResult result, Model model){

        model.addAttribute("idMarche", idMarche );
        model.addAttribute("idSitePrestataire", idSitePrestataire );

        if (result.hasErrors()) {
            model.addAttribute("idMarche", idMarche );
            model.addAttribute("idSitePrestataire", idSitePrestataire );
            return "marche/marche/info/office/add";
        }


        SitePrestataireBean sitePrestataire = sitePrestataireService.findById( idSitePrestataire );
        siteOfficeBean.setSitePrestataire( sitePrestataire );
        siteOfficeService.save( siteOfficeBean );

        return "redirect:/marche/info/detail/"+ idMarche + "?addSuccess";
    }

    @GetMapping("/edit/{id}/{idMarche}/{idSitePrestataire}")
    public String edit(@PathVariable Long id,
                       @PathVariable Long idMarche,
                       @PathVariable Long idSitePrestataire,
                       Model model){
        SiteOfficeBean siteOfficeBean = siteOfficeService.findById( id );
        if( siteOfficeBean == null ){
            model.addAttribute("idMarche", idMarche );
            model.addAttribute("idSitePrestataire", idSitePrestataire );
            return "marche/marche/info/office/add";
        }
        model.addAttribute( siteOfficeBean );
        model.addAttribute("idMarche", idMarche );
        model.addAttribute("idSitePrestataire", idSitePrestataire );
        return "marche/marche/info/office/update";
    }

    @PostMapping("/update/{id}/{idMarche}/{idSitePrestataire}")
    public String update(@PathVariable Long id,
                         @PathVariable Long idMarche,
                         @PathVariable Long idSitePrestataire,
                         @ModelAttribute @Valid SiteOfficeBean siteOfficeBean,
                         BindingResult result, Model model){

        model.addAttribute("idMarche", idMarche );
        model.addAttribute("idSitePrestataire", idSitePrestataire );

        if (result.hasErrors()) {
            model.addAttribute("idMarche", idMarche );
            model.addAttribute("idSitePrestataire", idSitePrestataire );
            return "marche/marche/info/office/update";
        }

        SitePrestataireBean sitePrestataire = sitePrestataireService.findById( idSitePrestataire );
        siteOfficeBean.setSitePrestataire( sitePrestataire );
        siteOfficeService.save( siteOfficeBean );

        return "redirect:/marche/info/detail/"+ idMarche + "?updateSuccess";
    }

    @GetMapping("/delete/{id}/{idMarche}/{idSitePrestataire}")
    public String delete(@PathVariable Long id,
                         @PathVariable Long idMarche,
                         @PathVariable Long idSitePrestataire,
                         Model model){


        model.addAttribute("idMarche", idMarche );
        model.addAttribute("idSitePrestataire", idSitePrestataire );

        siteOfficeService.delete( id );

        return "redirect:/marche/info/detail/"+ idMarche+ "?deleteSuccess";
    }
}
