package fr.rbo.elitweb.controller;

import fr.rbo.elitweb.beans.EmpruntBean;
import fr.rbo.elitweb.beans.UserBean;
import fr.rbo.elitweb.exceptions.NotFoundException;
import fr.rbo.elitweb.proxies.APIProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmpruntController {

    @Autowired
    APIProxy apiProxy;

//    @RequestMapping(value="/emprunts", method = RequestMethod.GET)
//    public String Emprunts(Model model, HttpSession httpSession){
//        EmpruntBean empruntCriteres =new EmpruntBean();
//        List<EmpruntBean> emprunts = apiProxy.findAllEmprunts();
//        model.addAttribute("status", "/emprunts");
//        model.addAttribute("empruntCriteres", empruntCriteres);
//        model.addAttribute("emprunts", emprunts);
//        return "recherche-emprunts-list";
//    }
    @RequestMapping(value="/mesemprunts", method = RequestMethod.GET)
    public String MesEmprunts(Model model, HttpSession httpSession){
        EmpruntBean empruntCriteres = new EmpruntBean();
        empruntCriteres.setUser(recupUser());
        List<EmpruntBean> emprunts = null;
        try {
            emprunts = apiProxy.rechercheEmpruntCriteres(empruntCriteres);
            model.addAttribute("status", "/mesemprunts");
        } catch(NotFoundException e){ model.addAttribute("status", "notfound"); }
        model.addAttribute("empruntCriteres", empruntCriteres);
        model.addAttribute("emprunts", emprunts);
        return "recherche-emprunts-list";
    }
    @RequestMapping(value="/mesemprunts/recherche", method = RequestMethod.POST)
    public String EmpruntsRecherche (Model model,
                                     @ModelAttribute("empruntCriteres") EmpruntBean empruntCriteres,
                                     HttpSession httpSession) {
        List<EmpruntBean> emprunts = null;
        empruntCriteres.setUser(recupUser());
        try {
            emprunts = apiProxy.rechercheEmpruntCriteres(empruntCriteres);
            model.addAttribute("status", "/emprunts/recherche");
        } catch(NotFoundException e){ model.addAttribute("status", "notfound"); }
        model.addAttribute("empruntCriteres", empruntCriteres);
        model.addAttribute("emprunts", emprunts);
        return "recherche-emprunts-list";
    }
    @RequestMapping(value = {"/details", "/emprunt/details"}, method = RequestMethod.GET)
    public String details(@RequestParam("empruntId") int empruntId, Model model){
        EmpruntBean emprunt = apiProxy.findEmpruntById(empruntId);
        model.addAttribute("emprunt", emprunt);
        return "emprunt-details";
    }

    private UserBean recupUser(){
        UserBean userCritere = new UserBean();
        userCritere.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return userCritere;
    }
}
