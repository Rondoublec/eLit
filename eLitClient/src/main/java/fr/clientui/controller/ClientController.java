package fr.clientui.controller;

import fr.clientui.beans.OuvrageBean;
import fr.clientui.proxies.MicroserviceOuvragesProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeArray.lastIndexOf;


@Controller
public class ClientController {

    @Autowired
    MicroserviceOuvragesProxy mOuvragesProxy;

    @RequestMapping(value={"/", "/Accueil"})
    public String accueil(Model model){

        List<OuvrageBean> ouvrages = mOuvragesProxy.findAll();
        model.addAttribute("ouvrages", ouvrages);
        return "Accueil";
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String details(@RequestParam("ouvrageId") int ouvrageId, Model model){

        OuvrageBean ouvrage = mOuvragesProxy.findById(ouvrageId);
        model.addAttribute("ouvrage", ouvrage);
        return "Details";
    }

}