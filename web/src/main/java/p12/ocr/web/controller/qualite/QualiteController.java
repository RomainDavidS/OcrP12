package p12.ocr.web.controller.qualite;

import p12.ocr.web.beans.qualite.BulletinBean;
import p12.ocr.web.beans.qualite.MarcheQualiteBean;
import p12.ocr.web.beans.qualite.PortageBean;
import p12.ocr.web.service.qualite.bulletin.IBulletinService;
import p12.ocr.web.service.qualite.marchequalite.IMarcheQualiteService;
import p12.ocr.web.service.qualite.portage.IPortageService;
import p12.ocr.web.technical.enums.typebulletin.TypeBulletin;
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
@RequestMapping("/qualite")
public class QualiteController {

    @Autowired
    private IBulletinService bulletinService;

    @ModelAttribute
    public List<BulletinBean> bulletinBeanList(){ return bulletinService.findAll();}

    @ModelAttribute
    public BulletinBean bulletinBean(){return new BulletinBean();}

    @Autowired
    private IMarcheQualiteService marcheQualiteService;

    @ModelAttribute
    public List<MarcheQualiteBean> marcheQualiteBeanList(){ return marcheQualiteService.findAll();}

    @Autowired
    private IPortageService portageService;

    @Autowired
    private Plugin plugin;

    @ModelAttribute
    public List<Field> fieldList (){

        List< Field > fieldList = Arrays.asList(
                new Field("name"),
                new Field("idBulletin")
        );
        return fieldList;
    }

    private  BindingResult resultController(BindingResult r, BulletinBean b ){

        List<FieldError> fieldErrorList = Arrays.asList();
        return plugin.resultController( r,  fieldErrorList ) ;
    }


    @GetMapping("/bulletin/all/{typeBulletin}")
    public String findAllByBulletin(@PathVariable TypeBulletin typeBulletin, Model model){
        model.addAttribute( typeBulletin );
        return "qualite/list";
    }

    @GetMapping("/add/{typeBulletin}")
    public String add( @PathVariable TypeBulletin typeBulletin, Model model){


        model.addAttribute( typeBulletin );
        return "qualite/add";

    }


    @PostMapping("/save/{typeBulletin}")
    public String save(@PathVariable TypeBulletin typeBulletin,
                       @ModelAttribute  @Valid BulletinBean bulletinBean,
                       BindingResult result,
                       Model model){

        if (result.hasErrors() )
            return "qualite/add";

        bulletinBean.setTypeBulletin( typeBulletin );
        BulletinBean bulletin =  bulletinService.save( bulletinBean );

        for (MarcheQualiteBean marche : marcheQualiteBeanList() )
            portageService.save(new PortageBean( bulletin,marche ) );

        return "redirect:/qualite/bulletin/all/" + typeBulletin + "?addSuccess";

    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        BulletinBean bulletinBean = bulletinService.findById( id );
        if(bulletinBean == null)
            return "index";

        model.addAttribute(  bulletinBean );

        return "qualite/update";
    }

    @PostMapping("/update/{id}")
    public String update( @PathVariable Long id, @Valid BulletinBean bulletinBean, BindingResult result,Model model){


        if (result.hasErrors() ) {
            bulletinBean = bulletinService.findById( id );
            model.addAttribute(  bulletinBean );
            return "qualite/update";
        }

        for (PortageBean portageBean : bulletinBean.getPortageList() ) {
            PortageBean portage = portageService.findById( portageBean.getId() );
            portage.setPortageDate( portageBean.getPortageDate() );
            portage.setBulletin( bulletinBean );
            portageService.save( portage );
        }

        bulletinService.update( bulletinBean );
        return "redirect:/qualite/bulletin/all/" + bulletinBean.getTypeBulletin() + "?updateSuccess";

    }


}
