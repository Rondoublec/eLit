package fr.rbo.elitweb.controller;

import fr.rbo.elitweb.beans.EmpruntBean;
import fr.rbo.elitweb.beans.OuvrageBean;
import fr.rbo.elitweb.exceptions.NotFoundException;
import fr.rbo.elitweb.proxies.APIProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OuvragesController {

    @Autowired
    APIProxy apiProxy;

    @RequestMapping(value="/ouvrages", method = RequestMethod.GET)
    public String Ouvrages(Model model, HttpSession httpSession
            ,final RedirectAttributes redirectAttributes){
        OuvrageBean ouvrageCriteres =new OuvrageBean();
        List<OuvrageBean> ouvrages = null;
        try {
            ouvrages = apiProxy.rechercheOuvrage(ouvrageCriteres);
//        } catch(NotFoundException e){ model.addAttribute("status", "emptyList"); }
        } catch(NotFoundException e){}
        model.addAttribute("ouvrageCriteres", ouvrageCriteres);
        model.addAttribute("ouvrages", ouvrages);
        return "recherche-ouvrages-list";
    }
    @RequestMapping(value="/ouvrages/recherche", method = RequestMethod.POST)
    public String OuvragesRecherche (Model model,
                                     @ModelAttribute("ouvrageCriteres") OuvrageBean ouvrageCriteres,
                                     HttpSession httpSession) {
        List<OuvrageBean> ouvrages = null;
        try {
            ouvrages = apiProxy.rechercheOuvrage(ouvrageCriteres);
        } catch(NotFoundException e){}
        model.addAttribute("ouvrageCriteres", ouvrageCriteres);
        model.addAttribute("ouvrages", ouvrages);
        return "recherche-ouvrages-list";
    }
    @RequestMapping(value = "/ouvrage/details", method = RequestMethod.GET)
    public String details(@RequestParam("ouvrageId") int ouvrageId, Model model
            ,final RedirectAttributes redirectAttributes){
        OuvrageBean ouvrage = null;
        try {
            ouvrage = apiProxy.findOuvrageById(ouvrageId);
        } catch(NotFoundException e){
            redirectAttributes.addFlashAttribute("status","notFound");
            model.addAttribute("status", "notFound");
            return "redirect:/ouvrages";
        }
        model.addAttribute("ouvrage", ouvrage);
        return "ouvrage-details";
    }

}
