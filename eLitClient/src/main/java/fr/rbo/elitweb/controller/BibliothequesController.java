package fr.rbo.elitweb.controller;

import fr.rbo.elitweb.beans.BibliothequeBean;
import fr.rbo.elitweb.proxies.APIProxy;
import fr.rbo.elitweb.service.ClientAPIService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(BibliothequesController.class);

    @Autowired
    APIProxy apiProxy;
    @Autowired
    HttpServletRequest request;

    /**
     * Affiche la liste des bibliothèques
     * @param model
     * @param httpSession
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value="/bibliotheques", method = RequestMethod.GET)
    public String Ouvrages(Model model, HttpSession httpSession
            , final RedirectAttributes redirectAttributes){
        LOGGER.debug("Get /bibliotheques");
        List<BibliothequeBean> bibliotheques = apiProxy.findAllBibliotheques();
        model.addAttribute("bibliotheques", bibliotheques);
        return "choix-bibliotheque";
    }

    /**
     * Sauvegarde dans la session de l'utilisateur de la bibliothèque choisie
     * @param bibliothequeId
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/mabibliotheque", method = RequestMethod.GET)
    public String details(@RequestParam("bibliothequeId") int bibliothequeId, Model model
            , final RedirectAttributes redirectAttributes){
        LOGGER.debug("Get /mabibliotheque : " + bibliothequeId);
        request.getSession().setAttribute("bibliotheque", bibliothequeId);
        return "redirect:/ouvrages";
    }

}
