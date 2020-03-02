package p12.ocr.web.controller.agence;

import p12.ocr.web.beans.agence.ManagementVisuelBean;
import p12.ocr.web.service.agence.managementvisuel.IManagementVisuelService;
import p12.ocr.web.technical.buttonradio.InitButtonRadio;
import p12.ocr.web.technical.error.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/managementVisuel")
public class ManagementVisuelController {

    @Autowired
    private IManagementVisuelService managementVisuelService;

    @ModelAttribute
    public List<ManagementVisuelBean> managementVisuelBeanList(){ return managementVisuelService.findAll();}

    @ModelAttribute
    public ManagementVisuelBean managementVisuelBean(){
        ManagementVisuelBean managementVisuelBean = new ManagementVisuelBean();
        managementVisuelBean.setPublication( true );
        return managementVisuelBean;
    }

    @ModelAttribute("tagIsEnabled")
    public List<InitButtonRadio> initIsEnabledRadio(){
        List< InitButtonRadio > initButtonRadios = Arrays.asList(
                new InitButtonRadio(true,"Actif"),
                new InitButtonRadio(false,"Inactif")
        );
        return  initButtonRadios;
    }
    @ModelAttribute
    public List<Field> fieldList (){
        List< Field > fieldList = Arrays.asList(
                new Field("id"),
                new Field("name")
        );
        return fieldList;
    }
    @GetMapping("/all")
    public String list(Model model){
        return "agence/managementvisuel/list";
    }



    @GetMapping("/add")
    public String add(Model model){
        return "agence/managementvisuel/add";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid ManagementVisuelBean managementVisuelBean, BindingResult result, Model model, HttpSession session){


        if (result.hasErrors() )
            return "agence/managementvisuel/add";

        managementVisuelService.save( managementVisuelBean );
        session.removeAttribute("managementVisuel");
        return "redirect:/managementVisuel/all?addSuccess";

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        ManagementVisuelBean managementVisuelBean = managementVisuelService.findById( id );
        if(managementVisuelBean == null)
            return "agence/managementvisuel/add";

        model.addAttribute(  managementVisuelBean );
        return "agence/managementvisuel/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable String id, @Valid ManagementVisuelBean managementVisuelBean, BindingResult result, HttpSession session){


        if (result.hasErrors())
            return "agence/managementvisuel/update";

        managementVisuelService.update( managementVisuelBean );

        session.removeAttribute("managementVisuel");
        return "redirect:/managementVisuel/all?updateSuccess";

    }


}
