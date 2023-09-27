package com.adacielochallenge.prospect.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.adacielochallenge.prospect.dto.LegalEntityCreateDTO;
import com.adacielochallenge.prospect.dto.NaturalPersonCreateDTO;
import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.model.LegalEntity;
import com.adacielochallenge.prospect.model.NaturalPerson;
import com.adacielochallenge.prospect.repository.ClientRepository;

@Service
public class ClientService {
    private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);

    private final NaturalPersonService naturalPersonService;
    private final LegalEntityService legalEntityService;

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(
            ClientRepository clientRepository,
            LegalEntityService legalEntityService,
            NaturalPersonService naturalPersonService) {
        this.clientRepository = clientRepository;

        this.legalEntityService = legalEntityService;
        this.naturalPersonService = naturalPersonService;

    }

    public LegalEntity createLegalEntity(LegalEntityCreateDTO legalEntityCreateDTO) {
        try {

            if (legalEntityService.legalEntityExists(legalEntityCreateDTO)) {
                throw new DataIntegrityViolationException(
                        "legal entity client prospect has already been created.");
            }
            LegalEntity legalEntity = legalEntityService.createLegalEntity(legalEntityCreateDTO);
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
                        "natural person client prospect has already been created.");
            }
            NaturalPerson naturalPerson = naturalPersonService.createNaturalPerson(naturalPersonCreateDTO);
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

    public Client retrieveClient(long id) throws EmptyResultDataAccessException {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return client.get();
    }

}
