package fr.rbo.elitweb.controller;

import fr.rbo.elitweb.beans.UserBean;
import fr.rbo.elitweb.proxies.APIProxy;
import fr.rbo.elitweb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private final UserService userService;

    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    APIProxy apiProxy;

    @RequestMapping(value={"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model){
        LOGGER.debug("Get / , /index");
        return "index";
    }

}