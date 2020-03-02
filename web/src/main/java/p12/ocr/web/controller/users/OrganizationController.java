package p12.ocr.web.controller.users;

import p12.ocr.web.beans.users.OrganizationBean;
import p12.ocr.web.service.users.organization.IOrganizationService;
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
@RequestMapping("/users/organization")
public class OrganizationController {
    @Autowired
    private IOrganizationService organizationService;

    @ModelAttribute
    public List<OrganizationBean> organizationBeanList(){return  organizationService.findAll();}

    @ModelAttribute
    public OrganizationBean organizationBean(){return new OrganizationBean();}

    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("name" )
        );
        return fieldList;
    }



    @GetMapping("/administration")
    public String list(Model model){

        return "users/organization/administration/index";

    }

    @GetMapping("/add")
    public String add(Model model){

        return "users/organization/administration/add";

    }

    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid OrganizationBean organizationBean, BindingResult result, Model model){

        if (result.hasErrors())
            return "users/organization/administration/add";

        organizationService.save( organizationBean );

        return "redirect:/users/organization/administration?addSuccess";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        OrganizationBean organizationBean = organizationService.findById( id );
        if(organizationBean == null )
            return "users/organization/administration/add";


        model.addAttribute(  organizationBean );
        return "users/organization/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable Long id, @Valid OrganizationBean organizationBean, BindingResult result){

        if (result.hasErrors())
            return "users/organization/administration/update";

        organizationService.update( organizationBean );
        return "redirect:/users/organization/administration?updateSuccess";

    }


}
