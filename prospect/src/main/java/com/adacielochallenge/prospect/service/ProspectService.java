package com.adacielochallenge.prospect.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adacielochallenge.prospect.component.ProspectQueue;
import com.adacielochallenge.prospect.dto.ProspectUpdateDTO;
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
        shiftedProspect.setStatus(ProspectStatus.PROCESSING);
        clientRepository.save(shiftedProspect);
        return shiftedProspect;
    }

    public Client updateProspect(Long id, ProspectUpdateDTO prospectUpdateDTO) {
        ProspectStatus status = prospectUpdateDTO.getStatus();
        Optional<Client> possibleProspect = clientRepository.findById(id);
        if (possibleProspect.isEmpty()) {
            throw new IllegalStateException("prospect não encontrado");
        }
        Client prospect = possibleProspect.get();

        switch (status) {
            case ACCEPTED:
                prospect.update(status);
                break;

            case REJECTED:
                prospect.update(status);
                break;

            case NOT_PROCESSED:
                prospect.update(status);
                prospectQueue.unshift(prospect);
                break;

            default:
                return prospect;
        }

        clientRepository.save(prospect);
        return null;
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
