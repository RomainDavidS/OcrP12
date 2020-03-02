package p12.ocr.web.controller.prestataire;

import p12.ocr.web.beans.prestataire.CallCenterBean;
import p12.ocr.web.service.prestataire.callcenter.ICallCenterService;
import p12.ocr.web.technical.buttonradio.InitButtonRadio;
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
@RequestMapping("/callCenter")
public class CallCenterController {

    @Autowired
    private ICallCenterService callCenterService;

    @ModelAttribute
    public CallCenterBean callCenterBean(){
        CallCenterBean callCenterBean =new CallCenterBean();
        callCenterBean.setEnabled(true);
        return callCenterBean; }

    @ModelAttribute
    public List<CallCenterBean> callCenterBeanList(){ return callCenterService.findAll();}


    @Autowired
    private SimpleDate simpleDate;

    @Autowired
    private Plugin plugin;

    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("name"),
                new Field("phone"),
                new Field("openingTime")
        );
        return fieldList;
    }

    @ModelAttribute("tagIsEnabled")
    public List<InitButtonRadio> initIsEnabledRadio(){
        List< InitButtonRadio > initButtonRadios = Arrays.asList(
                new InitButtonRadio(true,"Actif"),
                new InitButtonRadio(false,"Inactif")
        );
        return  initButtonRadios;
    }

    private BindingResult resultController(BindingResult r, CallCenterBean c ){

        List<FieldError> fieldErrorList = Arrays.asList(
                //  new FieldError("idPrestataire",  "L'entreprise prestaire est obligatoire.", m.getIdPrestataire(),false )

        );
        return plugin.resultController( r,  fieldErrorList ) ;
    }

    @GetMapping("/administration")
    public String list(Model model){
        return "prestataire/callcenter/administration/index";
    }

    @GetMapping("/add")
    public String add(Model model){return "prestataire/callcenter/administration/add"; }


    @PostMapping("/save")
    public String save(@ModelAttribute  @Valid CallCenterBean callCenterBean, BindingResult result, Model model){


        if (result.hasErrors() )
            return "prestataire/callcenter/administration/add";

        callCenterService.save( callCenterBean );
        return "redirect:/callCenter/administration?addSuccess";

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        CallCenterBean callCenterBean = callCenterService.findById( id );
        if ( callCenterBean == null )
            return "prestataire/callcenter/administration/add";

        model.addAttribute(  callCenterBean );
        return "prestataire/callcenter/administration/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable Long id, @Valid CallCenterBean callCenterBean, BindingResult result){

        result =  resultController( result,  callCenterBean ) ;

        if (result.hasErrors())
            return "prestataire/callcenter/administration/update";

        callCenterService.update( callCenterBean );
        return "redirect:/callCenter/administration?updateSuccess";

    }

}
