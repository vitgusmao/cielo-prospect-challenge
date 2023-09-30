package com.adacielochallenge.prospect.component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.service.ProspectService;

@Component
public class ProspectQueueStarter {

    private static final Logger LOG = LoggerFactory.getLogger(ProspectQueueStarter.class);

    private final ProspectService prospectService;
    private final ProspectQueue prospectQueue;

    @Autowired
    public ProspectQueueStarter(ProspectService prospectService, ProspectQueue prospectQueue) {
        this.prospectService = prospectService;
        this.prospectQueue = prospectQueue;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationStart() {
        LOG.info("carregando fila de prospects");

        List<Client> prospects = prospectService.loadQueueInitialData();
        prospectQueue.setProspects(prospects);
    }
}