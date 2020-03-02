package p12.ocr.web.controller.marche;

import p12.ocr.web.beans.marche.MarcheBean;
import p12.ocr.web.beans.prestataire.EntrepriseBean;
import p12.ocr.web.beans.prestataire.SitePrestataireBean;
import p12.ocr.web.service.marche.marche.IMarcheService;
import p12.ocr.web.service.prestataire.entreprise.IEntrepriseService;
import p12.ocr.web.service.prestataire.siteprestataire.ISitePrestataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/marche/info")
public class MarcheInfoController {

    @Autowired
    private IMarcheService marcheService;

    @Autowired
    private ISitePrestataireService sitePrestataireService;

    @Autowired
    private IEntrepriseService entrepriseService;


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        MarcheBean marcheBean = marcheService.findById( id );
        SitePrestataireBean sitePrestataireBean = sitePrestataireService.findById( marcheBean.getIdSite() );
        EntrepriseBean prestataire = entrepriseService.findById( marcheBean.getIdPrestataire() );
        EntrepriseBean sousTraitant = entrepriseService.findById( marcheBean.getIdSousTraitant() );


        model.addAttribute("idMarche", id );
        model.addAttribute("idSitePrestataire", marcheBean.getIdSite() );
        model.addAttribute( marcheBean );
        model.addAttribute( sitePrestataireBean );
        model.addAttribute( "prestataire", prestataire );
        model.addAttribute("sousTraitant", sousTraitant );

        return "marche/marche/info/detail";
    }





}
