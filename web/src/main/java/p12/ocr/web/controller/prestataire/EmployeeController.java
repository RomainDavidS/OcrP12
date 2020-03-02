package p12.ocr.web.controller.prestataire;

import p12.ocr.web.beans.prestataire.EmployeeBean;
import p12.ocr.web.service.prestataire.employee.IEmployeeService;
import p12.ocr.web.technical.date.SimpleDate;
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
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @ModelAttribute
    public EmployeeBean employeeBean(){return new EmployeeBean();}

    @ModelAttribute
    public List<EmployeeBean> employeeBeanList(){return employeeService.findAll();}

    @Autowired
    private SimpleDate simpleDate;

    @Autowired
    private Plugin plugin;

    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList();
        return fieldList;
    }

    private BindingResult resultController(BindingResult r, EmployeeBean e ){

        List<FieldError> fieldErrorList = Arrays.asList(
                //  new FieldError("idPrestataire",  "L'entreprise prestaire est obligatoire.", m.getIdPrestataire(),false )

        );
        return plugin.resultController( r,  fieldErrorList ) ;
    }

    @GetMapping("/administration")
    public String list(Model model){
        return "prestataire/employee/administration/index";
    }

    @GetMapping("/add")
    public String add(Model model){return "prestataire/employee/administration/add";}


    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid EmployeeBean employeeBean, BindingResult result, Model model){

        result =  resultController( result,  employeeBean ) ;


        if (result.hasErrors() )
            return "prestataire/employee/administration/add";

        employeeService.save( employeeBean );

        return "redirect:/employee/administration?addSuccess";

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        EmployeeBean employeeBean = employeeService.findById( id );
        if(employeeBean == null)
            return "prestataire/employee/administration/add";

        model.addAttribute(  employeeBean );
        return "prestataire/employee/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable Long id, @Valid EmployeeBean employeeBean, BindingResult result){

        result =  resultController( result,  employeeBean ) ;

        if (result.hasErrors())
            return "prestataire/employee/administration/update";

        employeeService.update( employeeBean );
        return "redirect:/employee/administration?updateSuccess";

    }


}
