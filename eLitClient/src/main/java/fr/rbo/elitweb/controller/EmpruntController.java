package fr.rbo.elitweb.controller;

import fr.rbo.elitweb.beans.EmpruntBean;
import fr.rbo.elitweb.beans.UserBean;
import fr.rbo.elitweb.exceptions.NotAcceptableException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EmpruntController {

    @Autowired
    APIProxy apiProxy;

    @RequestMapping(value="/mesemprunts", method = RequestMethod.GET)
    public String MesEmprunts(Model model, HttpSession httpSession
            ,final RedirectAttributes redirectAttributes){
        EmpruntBean empruntCriteres = new EmpruntBean();
        empruntCriteres.setUser(recupUser());
        empruntCriteres.setEmpruntRendu(false);
        List<EmpruntBean> emprunts = null;
        try {
            emprunts = apiProxy.rechercheEmpruntCriteres(empruntCriteres);
        } catch(NotFoundException e){}
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
        } catch(NotFoundException e){}
        model.addAttribute("empruntCriteres", empruntCriteres);
        model.addAttribute("emprunts", emprunts);
        return "recherche-emprunts-list";
    }
    @RequestMapping(value = "/emprunt/details", method = RequestMethod.GET)
    public String details(@RequestParam("empruntId") int empruntId, Model model
            ,final RedirectAttributes redirectAttributes){
        EmpruntBean emprunt = null;
        try {
            emprunt = apiProxy.findEmpruntById(empruntId);
        } catch(NotFoundException e){
            redirectAttributes.addFlashAttribute("status","notFound");
            model.addAttribute("status", "notFound");
            return "redirect:/mesemprunts";
        }
        if (!emprunt.getUser().getEmail().equals(recupUser().getEmail())){
            redirectAttributes.addFlashAttribute("status","notAuthorize");
            model.addAttribute("status","notAuthorize");
            return "redirect:/mesemprunts";
        }
        model.addAttribute("emprunt", emprunt);
        return "details-emprunt";
    }
    @RequestMapping(value = "/emprunt/plus", method = RequestMethod.GET)
    public String plus(@RequestParam("empruntId") int empruntId, Model model
            ,final RedirectAttributes redirectAttributes){
        EmpruntBean emprunt = null;
        try {
            emprunt = apiProxy.findEmpruntById(empruntId);
        } catch(NotFoundException e){
            redirectAttributes.addFlashAttribute("status","notFound");
            model.addAttribute("plus", "notFound");
            return "redirect:/mesemprunts";
        }
        if (!emprunt.getUser().getEmail().equals(recupUser().getEmail())){
            redirectAttributes.addFlashAttribute("status","notAuthorize");
            model.addAttribute("status","notAuthorize");
            return "redirect:/mesemprunts";
        }
        try {
            emprunt = apiProxy.prolongeEmpruntById(empruntId);
            redirectAttributes.addFlashAttribute("plus","success");
            model.addAttribute("plus", "success");
        } catch(NotAcceptableException e){
            redirectAttributes.addFlashAttribute("plus","unsuccess");
            model.addAttribute("plus", "unsuccess");
        }
        return "redirect:/mesemprunts";
    }

    private UserBean recupUser(){
        UserBean userCritere = new UserBean();
        userCritere.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return userCritere;
    }

}
