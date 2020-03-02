package p12.ocr.web.controller.marche;

import p12.ocr.web.beans.marche.TypeMarcheBean;
import p12.ocr.web.service.marche.typemarche.ITypeMarcheService;
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
@RequestMapping("/typeMarche")
public class TypeMarcheController {

    @Autowired
    private ITypeMarcheService typeMarcheService;

    @ModelAttribute
    public List<TypeMarcheBean> typeMarcheBeanList(){return  typeMarcheService.findAll();}

    @ModelAttribute
    public TypeMarcheBean typeMarcheBean(){return new TypeMarcheBean();}



    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("name" ),
                new Field("gender" )
        );
        return fieldList;
    }
    @Autowired
    private Plugin plugin;

    private  BindingResult resultController(BindingResult r, TypeMarcheBean t ){

        List<FieldError> fieldErrorList = Arrays.asList(
                new FieldError("gender",  "Vous devez sélectionner un genre de marché.",t.getGender().getCode(),false )
        );
        return plugin.resultController( r,  fieldErrorList ) ;
    }

    @GetMapping("/administration")
    public String list(Model model){

        return "marche/typemarche/administration/index";

    }

    @GetMapping("/add")
    public String add(Model model){return "marche/typemarche/administration/add";}

    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid TypeMarcheBean typeMarcheBean, BindingResult result, Model model){

        result =  resultController( result,  typeMarcheBean ) ;

        if (result.hasErrors())
            return "marche/typemarche/administration/add";

        typeMarcheService.save( typeMarcheBean );

        return "redirect:/typeMarche/administration?addSuccess";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        TypeMarcheBean typeMarcheBean = typeMarcheService.findById( id );
        if(typeMarcheBean == null)
            return "marche/typemarche/administration/add";

        model.addAttribute(  typeMarcheBean );
        return "marche/typemarche/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable Long id, @Valid TypeMarcheBean typeMarcheBean, BindingResult result, Model model){
        result =  resultController( result,  typeMarcheBean ) ;

        if (result.hasErrors())
            return "marche/typemarche/administration/update";

        typeMarcheService.update( typeMarcheBean );
        return "redirect:/typeMarche/administration?updateSuccess";

    }

}
