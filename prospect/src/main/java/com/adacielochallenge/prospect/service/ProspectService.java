package com.adacielochallenge.prospect.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adacielochallenge.prospect.component.ProspectQueue;
import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.model.ProspectStatus;
import com.adacielochallenge.prospect.repository.ClientRepository;

@Service
public class ProspectService {

    private final ClientRepository clientRepository;
    private final ProspectQueue prospectQueue;

    @Autowired
    public ProspectService(ClientRepository clientRepository, ProspectQueue prospectQueue) {
        this.clientRepository = clientRepository;
        this.prospectQueue = prospectQueue;
    }

    public List<Client> listProspects() {
        List<Client> clientList = prospectQueue.getProspects();

        return clientList;

    }

    public Client shiftProspects() throws IllegalStateException {
        Client shiftedProspect = prospectQueue.shift();
        return shiftedProspect;
    }

    public List<Client> loadQueueInitialData() {
        List<Client> clientList = clientRepository.findByStatus(ProspectStatus.NOT_PROCESSED);

        Collections.sort(clientList, new Comparator<Client>() {
            public int compare(Client o1, Client o2) {
                return o2.getCreatedOn().compareTo(o1.getCreatedOn());
            }
        });

        return clientList;
    }
}
