package p12.ocr.web.controller.marche;

import p12.ocr.web.beans.marche.IndemnisationBean;
import p12.ocr.web.beans.marche.MarcheBean;
import p12.ocr.web.service.marche.indemnisation.IIndemnisationService;
import p12.ocr.web.service.marche.marche.IMarcheService;
import p12.ocr.web.service.prestataire.siteprestataire.ISitePrestataireService;
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
@RequestMapping("/marche/info/indemnisation")
public class IndemnisationController {

    @Autowired
    private IMarcheService marcheService;

    @Autowired
    private ISitePrestataireService sitePrestataireService;

    @Autowired
    private IIndemnisationService indemnisationService;

    @ModelAttribute
    public IndemnisationBean indemnisationBean(){return new IndemnisationBean();}

    @Autowired
    private Plugin plugin;

    private  BindingResult resultController(BindingResult r, IndemnisationBean i ){

        List<FieldError> fieldErrorList = Arrays.asList();
        return plugin.resultController( r,  fieldErrorList ) ;
    }
    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("pdl" ),
                new Field("clientName" ),
                new Field("reclamationDate" ),
                new Field("reclamation" ),
                new Field("status")
        );
        return fieldList;
    }

    @GetMapping("/all/{id}")
    public String findAllByMarche(@PathVariable Long id, Model model){
        MarcheBean marcheBean = marcheService.findById( id );


        model.addAttribute("idMarche", id );
        model.addAttribute( marcheBean );

        return "marche/marche/info/indemnisation/list";
    }

    @GetMapping("/add/{id}")
    public String add(@PathVariable Long id,Model model){

        model.addAttribute("idMarche", id);

        return "marche/marche/info/indemnisation/add";

    }


    @PostMapping("/save/{id}")
    public String save(@PathVariable Long id, @ModelAttribute @Valid IndemnisationBean indemnisationBean, BindingResult result, Model model){

        result =  resultController( result,  indemnisationBean ) ;


        if (result.hasErrors() ) {
            model.addAttribute("idMarche", id );
            return "marche/marche/info/indemnisation/add";
        }
        MarcheBean marche = marcheService.findById( id );
        indemnisationBean.setMarche( marche );
        indemnisationService.save( indemnisationBean );
        return "redirect:/marche/info/indemnisation/all/"+ id +"?addSuccess";

    }


    @GetMapping("/edit/{id}/{idMarche}")
    public String edit(@PathVariable Long id, @PathVariable Long idMarche, Model model) {
        IndemnisationBean indemnisationBean = indemnisationService.findById( id );
        if(indemnisationBean == null){
            model.addAttribute("idMarche", idMarche);
            return "marche/marche/info/indemnisation/add";
        }
        model.addAttribute("idMarche", idMarche );
        model.addAttribute(  indemnisationBean );
        return "marche/marche/info/indemnisation/update";
    }

    @PostMapping("/update/{id}/{idMarche}")
    public String update( @PathVariable Long id, @PathVariable Long idMarche, @Valid IndemnisationBean indemnisationBean, BindingResult result,Model model){

        result =  resultController( result,  indemnisationBean ) ;

        if (result.hasErrors()) {
            model.addAttribute("idMarche", idMarche );
            return "marche/marche/info/indemnisation/update";
        }
        MarcheBean marche = marcheService.findById( idMarche );
        indemnisationBean.setMarche( marche );
        indemnisationService.update( indemnisationBean );
        return "redirect:/marche/info/indemnisation/all/"+ idMarche + "?updateSuccess";

    }




}
