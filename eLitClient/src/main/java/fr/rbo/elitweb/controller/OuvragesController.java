package fr.rbo.elitweb.controller;

import fr.rbo.elitweb.beans.OuvrageBean;
import fr.rbo.elitweb.proxies.APIProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class OuvragesController {

    @Autowired
    APIProxy apiProxy;

    @RequestMapping(value="/ouvrages", method = RequestMethod.GET)
    public String accueil(Model model){
        List<OuvrageBean> ouvrages = apiProxy.findAll();
        model.addAttribute("ouvrages", ouvrages);
        return "ouvrages";
    }
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(@RequestParam("ouvrageId") int ouvrageId, Model model){
        OuvrageBean ouvrage = apiProxy.findById(ouvrageId);
        model.addAttribute("ouvrage", ouvrage);
        return "details";
    }




}
