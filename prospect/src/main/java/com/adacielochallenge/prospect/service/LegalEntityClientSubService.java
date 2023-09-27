package com.adacielochallenge.prospect.service;

import java.util.List;

import com.adacielochallenge.prospect.dto.ClientCreateDTO;
import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.model.LegalEntity;
import com.adacielochallenge.prospect.repository.LegalEntityRepository;

public class LegalEntityClientSubService implements ClientSubService {

    private final LegalEntityRepository legalEntityRepository;

    public LegalEntityClientSubService(LegalEntityRepository legalEntityRepository) {
        this.legalEntityRepository = legalEntityRepository;
    }

    @Override
    public Client createClient(ClientCreateDTO clientCreateDTO) {
        Client client = new Client();

        LegalEntity legalEntity = new LegalEntity();

        legalEntity.setCnpj(clientCreateDTO.getCnpj());
        legalEntity.setCorporateReason(clientCreateDTO.getCorporateReason());
        legalEntity.setMcc(clientCreateDTO.getMcc());
        legalEntity.setContactCpf(clientCreateDTO.getCpf());
        legalEntity.setContactName(clientCreateDTO.getName());
        legalEntity.setContactEmail(clientCreateDTO.getEmail());

        client.setLegalEntity(legalEntity);

        legalEntityRepository.save(legalEntity);

        return client;
    }

    @Override
    public Boolean clientExists(ClientCreateDTO clientCreateDTO) {
        return legalEntityRepository.existsByCnpj(clientCreateDTO.getCnpj());
    }

}
