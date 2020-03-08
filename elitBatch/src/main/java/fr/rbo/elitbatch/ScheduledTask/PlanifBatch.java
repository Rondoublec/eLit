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
public class PlanifBatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlanifBatch.class);

    @Autowired
    private APIProxy apiProxy;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RelanceRetards relanceRetards;

    private final ClientAPIService clientService;

    public PlanifBatch(ClientAPIService clientService) {
        this.clientService = clientService;
    }

    /**
     * Planification du batch
     */
    //toutes les 2 minutes
    @Scheduled(cron = "0 */2 * ? * *")
    //une fois par jour à midi
    //@Scheduled(cron = "0 0 12 * * ?")

    public void planifBatchCron() {
        LOGGER.debug("Lancement du batch");

        relanceRetards.mailsDeRelances();

    }
}