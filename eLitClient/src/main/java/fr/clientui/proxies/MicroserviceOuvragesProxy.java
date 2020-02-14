package fr.clientui.proxies;

import fr.clientui.beans.OuvrageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "elitapi",url = "localhost:8088")
public interface MicroserviceOuvragesProxy {

    @GetMapping(value = "/ouvrages")
    List<OuvrageBean> findAll();

    @GetMapping( value = "/ouvrage/{id}")
    OuvrageBean findById(@PathVariable("id") int id);

}
