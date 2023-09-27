package com.adacielochallenge.prospect.service;

import com.adacielochallenge.prospect.dto.ClientCreateDTO;
import com.adacielochallenge.prospect.model.NaturalPerson;
import com.adacielochallenge.prospect.repository.NaturalPersonRepository;

public class NaturalPersonClientSubService implements ClientSubService {

    private final NaturalPersonRepository naturalPersonRepository;

    public NaturalPersonClientSubService(NaturalPersonRepository naturalPersonRepository) {
        this.naturalPersonRepository = naturalPersonRepository;
    }

    @Override
    public void createClient(ClientCreateDTO clientCreateDTO) {
        NaturalPerson naturalPerson = new NaturalPerson();

        naturalPerson.setCpf(clientCreateDTO.getCpf());
        naturalPerson.setName(clientCreateDTO.getName());
        naturalPerson.setMcc(clientCreateDTO.getMcc());
        naturalPerson.setEmail(clientCreateDTO.getEmail());

        naturalPersonRepository.save(naturalPerson);

    }

    @Override
    public Boolean clientExists(ClientCreateDTO clientCreateDTO) {
        return naturalPersonRepository.existsByCpf(clientCreateDTO.getCpf());
    }

}
