package com.adacielochallenge.prospect.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adacielochallenge.prospect.dto.ClientCreateDTO;
import com.adacielochallenge.prospect.dto.ClientType;
import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.model.LegalEntity;
import com.adacielochallenge.prospect.model.NaturalPerson;
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

    private Boolean isClientCreated(ClientSubService subService, ClientCreateDTO clientCreateDTO) {
        return subService.isClientCreated(clientCreateDTO);
    }

    public void createClient(ClientCreateDTO clientCreateDTO) {
        try {
            ClientSubService subService = this.getSubService(clientCreateDTO);

            this.isClientCreated(subService, clientCreateDTO);

            Client client = subService.createClient(clientCreateDTO);

            clientRepository.save(client);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }

        LOG.info(clientCreateDTO.toString());
    }

    private ClientSubService getSubService(ClientCreateDTO clientCreateDTO) {
        ClientType clientType = clientCreateDTO.getClientType();
        switch (clientType) {
            case LEGAL_ENTITY:
                return new LegalEntityClientSubService(legalEntityRepository);

            case NATURAL_PERSON:
                return new NaturalPersonClientSubService(naturalPersonRepository);

            default:
                throw new IllegalArgumentException("Unsupported client type");
        }
    }
}
