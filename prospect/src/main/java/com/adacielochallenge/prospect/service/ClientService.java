package com.adacielochallenge.prospect.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.adacielochallenge.prospect.component.ProspectQueue;
import com.adacielochallenge.prospect.dto.ClientUpdateDTO;
import com.adacielochallenge.prospect.dto.LegalEntityCreateDTO;
import com.adacielochallenge.prospect.dto.NaturalPersonCreateDTO;
import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.model.LegalEntity;
import com.adacielochallenge.prospect.model.NaturalPerson;
import com.adacielochallenge.prospect.model.ProspectStatus;
import com.adacielochallenge.prospect.repository.ClientRepository;

@Service
public class ClientService {
    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);

    private final NaturalPersonService naturalPersonService;
    private final LegalEntityService legalEntityService;

    private final ClientRepository clientRepository;

    private final ProspectQueue prospectQueue;

    @Autowired
    public ClientService(
            ClientRepository clientRepository,
            LegalEntityService legalEntityService,
            NaturalPersonService naturalPersonService,
            ProspectQueue prospectQueue) {
        this.clientRepository = clientRepository;

        this.legalEntityService = legalEntityService;
        this.naturalPersonService = naturalPersonService;

        this.prospectQueue = prospectQueue;
    }

    public LegalEntity createLegalEntity(LegalEntityCreateDTO legalEntityCreateDTO) {
        try {

            if (legalEntityService.legalEntityExists(legalEntityCreateDTO)) {
                throw new DataIntegrityViolationException(
                        "prospect para essa pessoa jurídica já foi criado.");
            }
            LegalEntity legalEntity = legalEntityService.createLegalEntity(legalEntityCreateDTO);
            prospectQueue.unshift(legalEntity);
            return legalEntity;

        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }

    }

    public NaturalPerson createNaturalPerson(NaturalPersonCreateDTO naturalPersonCreateDTO) {
        try {

            if (naturalPersonService.naturalPersonExists(naturalPersonCreateDTO)) {
                throw new DataIntegrityViolationException(
                        "prospect para essa pessoa física já foi criado.");
            }
            NaturalPerson naturalPerson = naturalPersonService.createNaturalPerson(naturalPersonCreateDTO);
            prospectQueue.unshift(naturalPerson);
            return naturalPerson;

        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }

    }

    public List<Client> listClients() {
        List<Client> clientList = clientRepository.findAll();
        return clientList;
    }

    public Client retrieveClient(long id) throws IllegalStateException {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new IllegalStateException("nenhum cliente com o id fornecido foi encontrado.");
        }

        return client.get();
    }

    public Client updateClient(long id, ClientUpdateDTO clientUpdateDTO) throws IllegalStateException {
        Client client = this.retrieveClient(id);

        if (clientUpdateDTO.validate(client)) {
            client.update(clientUpdateDTO);
            client.setStatus(ProspectStatus.NOT_PROCESSED);
            prospectQueue.unshift(client);
            clientRepository.save(client);
            return null;
        }

        return client;
    }

    public void deleteClient(long id) {
        prospectQueue.remove(id);
        clientRepository.deleteById(id);
    }

}
