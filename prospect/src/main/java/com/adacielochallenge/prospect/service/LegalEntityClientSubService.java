package com.adacielochallenge.prospect.service;

import com.adacielochallenge.prospect.dto.ClientCreateDTO;
import com.adacielochallenge.prospect.model.LegalEntity;
import com.adacielochallenge.prospect.repository.LegalEntityRepository;

public class LegalEntityClientSubService implements ClientSubService {

    private final LegalEntityRepository legalEntityRepository;

    public LegalEntityClientSubService(LegalEntityRepository legalEntityRepository) {
        this.legalEntityRepository = legalEntityRepository;

    }

    @Override
    public void createClient(ClientCreateDTO clientCreateDTO) {

        LegalEntity legalEntity = new LegalEntity();

        legalEntity.setCnpj(clientCreateDTO.getCnpj());
        legalEntity.setCorporateReason(clientCreateDTO.getCorporateReason());
        legalEntity.setMcc(clientCreateDTO.getMcc());
        legalEntity.setContactCpf(clientCreateDTO.getCpf());
        legalEntity.setContactName(clientCreateDTO.getName());
        legalEntity.setContactEmail(clientCreateDTO.getEmail());

        legalEntityRepository.save(legalEntity);

    }

    @Override
    public Boolean clientExists(ClientCreateDTO clientCreateDTO) {
        return legalEntityRepository.existsByCnpj(clientCreateDTO.getCnpj());
    }

}
