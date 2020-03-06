package fr.rbo.elitweb.controller;

import fr.rbo.elitweb.beans.BibliothequeBean;
import fr.rbo.elitweb.proxies.APIProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BibliothequesController {
    @Autowired
    APIProxy apiProxy;
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value="/bibliotheques", method = RequestMethod.GET)
    public String Ouvrages(Model model, HttpSession httpSession
            , final RedirectAttributes redirectAttributes){
        List<BibliothequeBean> bibliotheques = apiProxy.findAllBibliotheques();
        model.addAttribute("bibliotheques", bibliotheques);
        return "choix-bibliotheque";
    }
    @RequestMapping(value = "/mabibliotheque", method = RequestMethod.GET)
    public String details(@RequestParam("bibliothequeId") int bibliothequeId, Model model
            , final RedirectAttributes redirectAttributes){
        request.getSession().setAttribute("bibliotheque", bibliothequeId);
        return "redirect:/ouvrages";
    }


}
