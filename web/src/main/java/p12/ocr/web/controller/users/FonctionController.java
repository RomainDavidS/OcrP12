package p12.ocr.web.controller.users;

import p12.ocr.web.beans.users.FonctionBean;
import p12.ocr.web.service.users.fonction.IFonctionService;
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
@RequestMapping("/users/fonction")
public class FonctionController {


    @Autowired
    private IFonctionService fonctionService;

    @ModelAttribute
    public List<FonctionBean> fonctionBeanList(){return  fonctionService.findAll();}

    @ModelAttribute
    public FonctionBean fonctionBean(){return new FonctionBean();}


    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("name" )
        );
        return fieldList;
    }


    @GetMapping("/administration")
    public String list(Model model){

        return "users/fonction/administration/index";

    }

    @GetMapping("/add")
    public String add(Model model){

        return "users/fonction/administration/add";

    }

    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid FonctionBean fonctionBean, BindingResult result, Model model){

        if (result.hasErrors())
            return "users/fonction/administration/add";

        fonctionService.save( fonctionBean );

        return "redirect:/users/fonction/administration?addSuccess";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        FonctionBean fonctionBean = fonctionService.findById( id );
        if( fonctionBean == null)
            return "users/fonction/administration/add";

        model.addAttribute(  fonctionBean );
        return "users/fonction/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable Long id, @Valid FonctionBean fonctionBean, BindingResult result){

        if (result.hasErrors())
            return "users/fonction/administration/update";

        fonctionService.update( fonctionBean );
        return "redirect:/users/fonction/administration?updateSuccess";

    }


}
