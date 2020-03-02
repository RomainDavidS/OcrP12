package p12.ocr.web.controller.users;
import p12.ocr.web.beans.users.TypeSiteBean;
import p12.ocr.web.service.users.typesite.ITypeSiteService;
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
@RequestMapping("/entreprise/typeSite")
public class TypeSiteController {
    @Autowired
    private ITypeSiteService typeSiteService;

    @ModelAttribute
    public List<TypeSiteBean> typeSiteBeanList(){return  typeSiteService.findAll();}

    @ModelAttribute
    public TypeSiteBean typeSiteBean(){return new TypeSiteBean();}



    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("name" )
        );
        return fieldList;
    }

    @GetMapping("/administration")
    public String list(Model model){

        return "entreprise/typesite/administration/index";

    }

    @GetMapping("/add")
    public String add(Model model){return "entreprise/typesite/administration/add"; }

    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid TypeSiteBean typeSiteBean, BindingResult result, Model model){

        if (result.hasErrors())
            return "entreprise/typesite/administration/add";

        typeSiteService.save( typeSiteBean );

        return "redirect:/entreprise/typeSite/administration?addSuccess";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        TypeSiteBean typeSiteBean = typeSiteService.findById( id );
        if (typeSiteBean == null )
            return "entreprise/typesite/administration/add";

        model.addAttribute(  typeSiteBean );
        return "entreprise/typesite/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable Long id, @Valid TypeSiteBean typeSiteBean, BindingResult result){

        if (result.hasErrors())
            return "entreprise/typesite/administration/update";

        typeSiteService.update( typeSiteBean );
        return "redirect:/entreprise/typeSite/administration?updateSuccess";

    }
}
