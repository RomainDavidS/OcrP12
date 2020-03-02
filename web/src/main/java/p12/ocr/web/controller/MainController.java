package p12.ocr.web.controller;

import p12.ocr.web.service.marche.marche.IMarcheService;
import p12.ocr.web.technical.menu.marche.MenuOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("menuOrganization")
public class MainController {
    @Autowired
    private IMarcheService marcheService;

    @ModelAttribute("menuOrganization")
    public List<MenuOrganization> menuOrganizationList(){return marcheService.getMenuMarche() ;}

    @GetMapping("/erreur")
    public String erreur(Model model){
        return "common/error/denied";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "users/user/administration/login";
    }


}
