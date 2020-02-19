package fr.clientui.service;

import fr.clientui.beans.RoleBean;
import fr.clientui.beans.UserBean;
import fr.clientui.exceptions.APIException;
import fr.clientui.exceptions.NotFoundException;
import fr.clientui.proxies.APIProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class ClientAPIService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientAPIService.class);

    @Autowired
    private APIProxy apiProxy;

    public UserBean creerUser (@Valid UserBean user) {
        try {
            UserBean userExistant = apiProxy.recupererUnUser(user.getId());
            LOGGER.info("User déjà existant pour cet id Web");
            return userExistant;
        } catch (NotFoundException e) {
            try {
                UserBean userACreer = new UserBean();
                userACreer.setEmail(user.getEmail());
                userACreer.setName(user.getName());
                userACreer.setLastName(user.getLastName());
                userACreer.setId(user.getId());
                userACreer.setPassword(user.getPassword());
                userACreer.setActive(user.isActive());
                userACreer.setRoles(user.getRoles());
                UserBean userCree = apiProxy.creerUnUser(userACreer);
                return  userCree;
            } catch (RuntimeException ex) {
                throw new APIException("Post User" ,ex.getMessage(),ex.getStackTrace().toString());
            }
        } catch (RuntimeException e) {
            throw new APIException("Post User" ,e.getMessage(),e.getStackTrace().toString());
        }
    }
    public UserBean recupererUnUserParEmail(String email) {
        try {
            UserBean user = apiProxy.recupererUnUserParEmail(email);
            return user;
        } catch (NotFoundException e) {
            return null;
        } catch (RuntimeException e) {
            throw new APIException("Get user par email" ,e.getMessage(),e.getStackTrace().toString());
        }
    }

    public RoleBean recupererUnRoleParRole(String role) {
        try {
            RoleBean roleTrouve = apiProxy.roleParRole(role);
            return roleTrouve;
        } catch (NotFoundException e) {
            return null;
        } catch (RuntimeException e) {
            throw new APIException("Get Role par role" ,e.getMessage(),e.getStackTrace().toString());
            //TODO logs
        }
    }
}
