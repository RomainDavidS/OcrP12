package p12.ocr.web.controller;

import p12.ocr.web.beans.agence.ManagementVisuelBean;
import p12.ocr.web.service.users.userentreprise.IUserEntrepriseService;
import p12.ocr.web.service.agence.managementvisuel.IManagementVisuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@SessionAttributes("managementVisuel")
public class AccueilController {

    @Autowired
    private IManagementVisuelService managementVisuelService;

    @ModelAttribute("managementVisuel")
    public List<ManagementVisuelBean> managementVisuelBeanList(){return managementVisuelService.findAll();}

    @Autowired
    private IUserEntrepriseService userEntrepriseService;


    @GetMapping("/")
    private String accueil(Model model, HttpSession session){return "index"; }

    @GetMapping("/connectSuccess")
    private String connectSuccess(Model model, HttpSession session){
        session.setAttribute("currentUsername", userEntrepriseService.getCurrentUserFullName() );
        userEntrepriseService.setDateLastConnexion();
        return "redirect:/";
    }
}
