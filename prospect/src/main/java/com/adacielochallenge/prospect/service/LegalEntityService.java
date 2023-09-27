package com.adacielochallenge.prospect.service;

import org.springframework.stereotype.Service;

import com.adacielochallenge.prospect.dto.LegalEntityCreateDTO;
import com.adacielochallenge.prospect.model.LegalEntity;
import com.adacielochallenge.prospect.model.NaturalPerson;
import com.adacielochallenge.prospect.repository.LegalEntityRepository;

@Service
public class LegalEntityService {

    private final LegalEntityRepository legalEntityRepository;

    public LegalEntityService(LegalEntityRepository legalEntityRepository) {
        this.legalEntityRepository = legalEntityRepository;
    }

    public LegalEntity createLegalEntity(LegalEntityCreateDTO legalEntityCreateDTO) {

        LegalEntity legalEntity = new LegalEntity();

        legalEntity.setCnpj(legalEntityCreateDTO.getCnpj());
        legalEntity.setCorporateReason(legalEntityCreateDTO.getCorporateReason());
        legalEntity.setMcc(legalEntityCreateDTO.getMcc());
        legalEntity.setContactCpf(legalEntityCreateDTO.getCpf());
        legalEntity.setContactName(legalEntityCreateDTO.getName());
        legalEntity.setContactEmail(legalEntityCreateDTO.getEmail());

        legalEntityRepository.save(legalEntity);
        return legalEntity;
    }

    public Boolean legalEntityExists(LegalEntityCreateDTO legalEntityCreateDTO) {
        return legalEntityRepository.existsByCnpj(legalEntityCreateDTO.getCnpj());
    }

}
