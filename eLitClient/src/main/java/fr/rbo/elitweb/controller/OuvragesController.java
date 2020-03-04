package fr.rbo.elitweb.controller;

import fr.rbo.elitweb.beans.OuvrageBean;
import fr.rbo.elitweb.proxies.APIProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OuvragesController {

    @Autowired
    APIProxy apiProxy;

    @RequestMapping(value="/ouvrages", method = RequestMethod.GET)
    public String Ouvrages(Model model, HttpSession httpSession){
        OuvrageBean ouvrageCriteres = new OuvrageBean();
        List<OuvrageBean> ouvrages = apiProxy.findAll();
        model.addAttribute("status", "/ouvrages");
        model.addAttribute("ouvrageCriteres", ouvrageCriteres);
        model.addAttribute("ouvrages", ouvrages);
        return "recherche-ouvrages-list";
    }
    @RequestMapping(value="/ouvrages/recherche", method = RequestMethod.POST)
    public String OuvragesRecherche (Model model,
                                     @ModelAttribute("ouvrageCriteres") OuvrageBean ouvrageCriteres,
                                     HttpSession httpSession) {
        List<OuvrageBean> ouvrages = apiProxy.findAll();
        model.addAttribute("status", "/ouvrages/recherche");
        model.addAttribute("ouvrageCriteres", ouvrageCriteres);
        model.addAttribute("ouvrages", ouvrages);
        return "recherche-ouvrages-list";
    }
    @RequestMapping(value = {"/details", "/ouvrage/details"}, method = RequestMethod.GET)
    public String details(@RequestParam("ouvrageId") int ouvrageId, Model model){
        OuvrageBean ouvrage = apiProxy.findById(ouvrageId);
        model.addAttribute("ouvrage", ouvrage);
        return "ouvrage-details";
    }




}