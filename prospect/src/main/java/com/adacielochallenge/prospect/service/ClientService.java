package com.adacielochallenge.prospect.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.adacielochallenge.prospect.dto.ClientCreateDTO;
import com.adacielochallenge.prospect.dto.ClientType;
import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.repository.ClientRepository;
import com.adacielochallenge.prospect.repository.LegalEntityRepository;
import com.adacielochallenge.prospect.repository.NaturalPersonRepository;

@Service
public class ClientService {
    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;
    private final NaturalPersonRepository naturalPersonRepository;
    private final LegalEntityRepository legalEntityRepository;

    @Autowired
    public ClientService(
            ClientRepository clientRepository,
            NaturalPersonRepository naturalPersonRepository,
            LegalEntityRepository legalEntityRepository) {
        this.clientRepository = clientRepository;
        this.naturalPersonRepository = naturalPersonRepository;
        this.legalEntityRepository = legalEntityRepository;

    }

    public void createClient(ClientCreateDTO clientCreateDTO) {

        ClientSubService subService = this.getSubService(clientCreateDTO);
        if (this.clientExists(subService, clientCreateDTO)) {
            throw new DataIntegrityViolationException("client pre registration was already created.");
        }
        subService.createClient(clientCreateDTO);
    }

    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    private Boolean clientExists(ClientSubService subService, ClientCreateDTO clientCreateDTO) {
        return subService.clientExists(clientCreateDTO);
    }

    private ClientSubService getSubService(ClientCreateDTO clientCreateDTO) {
        ClientType clientType = clientCreateDTO.getClientType();
        switch (clientType) {
            case LEGAL_ENTITY:
                return new LegalEntityClientSubService(legalEntityRepository, clientRepository);

            case NATURAL_PERSON:
                return new NaturalPersonClientSubService(naturalPersonRepository, clientRepository);

            default:
                throw new IllegalArgumentException("Unsupported client type");
        }
    }
}
