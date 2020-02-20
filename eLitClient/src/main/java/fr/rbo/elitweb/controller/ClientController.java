package fr.rbo.elitweb.controller;

import fr.rbo.elitweb.beans.UserBean;
import fr.rbo.elitweb.proxies.APIProxy;
import fr.rbo.elitweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {

    private final UserService userService;

    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    APIProxy apiProxy;

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model){
        return "index";
    }

//    @RequestMapping(value="/home", method = RequestMethod.GET)
//    public ModelAndView home(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserBean user = userService.findUserByEmail(auth.getName());
//        modelAndView.addObject("userName", "Bienvenue " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//        //modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//        modelAndView.setViewName("index");
//        return modelAndView;
//    }
}