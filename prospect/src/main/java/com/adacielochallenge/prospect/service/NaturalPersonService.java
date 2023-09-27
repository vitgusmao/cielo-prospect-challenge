package com.adacielochallenge.prospect.service;

import org.springframework.stereotype.Service;

import com.adacielochallenge.prospect.dto.NaturalPersonCreateDTO;
import com.adacielochallenge.prospect.model.NaturalPerson;
import com.adacielochallenge.prospect.repository.NaturalPersonRepository;

@Service
public class NaturalPersonService {

    private final NaturalPersonRepository naturalPersonRepository;

    public NaturalPersonService(NaturalPersonRepository naturalPersonRepository) {
        this.naturalPersonRepository = naturalPersonRepository;
    }

    public NaturalPerson createNaturalPerson(NaturalPersonCreateDTO naturalPersonCreateDTO) {
        NaturalPerson naturalPerson = new NaturalPerson();

        naturalPerson.setCpf(naturalPersonCreateDTO.getCpf());
        naturalPerson.setName(naturalPersonCreateDTO.getName());
        naturalPerson.setMcc(naturalPersonCreateDTO.getMcc());
        naturalPerson.setEmail(naturalPersonCreateDTO.getEmail());

        naturalPersonRepository.save(naturalPerson);

        return naturalPerson;
    }

    public Boolean naturalPersonExists(NaturalPersonCreateDTO naturalPersonCreateDTO) {
        return naturalPersonRepository.existsByCpf(naturalPersonCreateDTO.getCpf());
    }

}
