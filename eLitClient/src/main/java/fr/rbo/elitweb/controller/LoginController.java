package fr.rbo.elitweb.controller;

import fr.rbo.elitweb.beans.UserBean;
import fr.rbo.elitweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public ModelAndView login(Model model){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        UserBean userBean = new UserBean();
        modelAndView.addObject("userBean", userBean);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView creerNouveauUser(@Valid UserBean userBean, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (!bindingResult.hasErrors()) {
            UserBean userExists = userService.findUserByEmail(userBean.getEmail());
            if (userExists != null) {
                bindingResult.rejectValue("email", "error.user","Ce compte existe déjà");
            }
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(userBean);
            modelAndView.addObject("successMessage", "Le compte a été créé avec succès");
            modelAndView.addObject("userBean", userBean);
            modelAndView.setViewName("registration");
        }
        return modelAndView;
    }

}
