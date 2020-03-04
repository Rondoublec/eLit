package fr.rbo.elitweb.proxies;

import feign.Param;
import feign.RequestLine;
import fr.rbo.elitweb.beans.OuvrageBean;
import fr.rbo.elitweb.beans.RoleBean;
import fr.rbo.elitweb.beans.UserBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "elitapi",url = "localhost:8088")
public interface APIProxy {

    @RequestLine("GET /ouvrages")
    List<OuvrageBean> findAll();

    @RequestLine("GET /ouvrage/{id}")
    OuvrageBean findById(@Param("id") int id);

    @RequestLine("GET /user/{id}")
    UserBean recupererUnUser(@Param("id") Long id);

    @RequestLine("GET /user/email/{email}")
    UserBean recupererUnUserParEmail(@Param("email") String email);

    @RequestLine("POST /user/")
    UserBean creerUnUser(@RequestBody UserBean user);

    @RequestLine("GET /roles/role/{role}")
    RoleBean roleParRole(@Param("role") String role);

}