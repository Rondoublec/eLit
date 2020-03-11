package fr.rbo.elitbatch.ScheduledTask;

import fr.rbo.elitbatch.proxies.APIProxy;
import fr.rbo.elitbatch.service.ClientAPIService;
import fr.rbo.elitbatch.service.EmailService;
import fr.rbo.elitbatch.service.RelanceRetards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PlanificationBatchRelanceRetards {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanificationBatchRelanceRetards.class);

    @Autowired
    private RelanceRetards relanceRetards;

    private final ClientAPIService clientService;

    public PlanificationBatchRelanceRetards(ClientAPIService clientService) {
        this.clientService = clientService;
    }

    /**
     * Planification du batch de relance des retards de restitutions d'emprunts
     */
    //toutes les 2 minutes
    @Scheduled(cron = "0 */2 * ? * *")
    public void PlanificationBatchRelanceRetardsCron() {
        LOGGER.info("Lancement du batch");
        System.out.println( "DEBUT : Appel du traitement des relances ========================== ");
        relanceRetards.mailsDeRelances();
        System.out.println( " FIN  : Appel du traitement des relances ========================== ");

    }
}
