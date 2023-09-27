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

    public void createClient(ClientCreateDTO clientCreateDTO) {
        ClientType clientType = clientCreateDTO.getClientType();
        Client client = new Client();

        switch (clientType) {
            case LEGAL_ENTITY:
                LegalEntity legalEntity = new LegalEntity();

                legalEntity.setCnpj(clientCreateDTO.getCnpj());
                legalEntity.setCorporateReason(clientCreateDTO.getCorporateReason());
                legalEntity.setMcc(clientCreateDTO.getMcc());
                legalEntity.setContactCPF(clientCreateDTO.getCpf());
                legalEntity.setContactName(clientCreateDTO.getName());
                legalEntity.setContactEmail(clientCreateDTO.getEmail());

                legalEntityRepository.save(legalEntity);

                client.setLegalEntity(legalEntity);
                break;

            case NATURAL_PERSON:
                NaturalPerson naturalPerson = new NaturalPerson();

                naturalPerson.setCpf(clientCreateDTO.getCpf());
                naturalPerson.setName(clientCreateDTO.getName());
                naturalPerson.setMcc(clientCreateDTO.getMcc());
                naturalPerson.setEmail(clientCreateDTO.getEmail());

                naturalPersonRepository.save(naturalPerson);

                client.setNaturalPerson(naturalPerson);
                break;

            default:
                throw new IllegalArgumentException("Unsupported client type");
        }

        clientRepository.save(client);

        LOG.info(clientCreateDTO.toString());
    }
}
