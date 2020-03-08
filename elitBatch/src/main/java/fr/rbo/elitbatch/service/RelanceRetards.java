package fr.rbo.elitbatch.service;

import fr.rbo.elitbatch.beans.EmpruntBean;
import fr.rbo.elitbatch.beans.UserBean;
import fr.rbo.elitbatch.exceptions.NotFoundException;
import fr.rbo.elitbatch.proxies.APIProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RelanceRetards {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);


    @Autowired
    private APIProxy apiProxy;
    @Autowired
    private EmailService emailService;
    private final ClientAPIService clientService;

    public RelanceRetards(ClientAPIService clientService) {
        this.clientService = clientService;
    }

    public void mailsDeRelances() {
        LOGGER.debug("Début du traitement : mailsDeRelances");
        Date date = new Date();
        System.out.println(
                "Passage du batch d'envoi des mails de relances - " + date.toString());
        List<UserBean> listeUser = clientService.listeUser();
        UserBean userCritere = new UserBean();
        EmpruntBean empruntCriteres = new EmpruntBean();
        for (UserBean user : listeUser)  {
            List<EmpruntBean> emprunts = null;
            userCritere.setEmail(user.getEmail());
            empruntCriteres.setUser(userCritere);
            empruntCriteres.setEmpruntRendu(false);
            try {
                emprunts = apiProxy.rechercheEmpruntCriteres(empruntCriteres);
                if (!emprunts.isEmpty()) {
                    emailService.envoiEmailRelance(user, emprunts);
                    //TODO mettre à jour le champs du nombre de relances
                    //TODO mettre à jour la date de relance sur les emprunts
                }
            } catch(NotFoundException e){}
        }
    }

}
