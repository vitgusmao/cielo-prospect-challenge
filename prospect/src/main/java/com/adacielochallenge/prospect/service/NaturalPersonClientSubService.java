package com.adacielochallenge.prospect.service;

import java.util.List;

import com.adacielochallenge.prospect.dto.ClientCreateDTO;
import com.adacielochallenge.prospect.model.Client;
import com.adacielochallenge.prospect.model.NaturalPerson;
import com.adacielochallenge.prospect.repository.NaturalPersonRepository;

public class NaturalPersonClientSubService implements ClientSubService {

    private final NaturalPersonRepository naturalPersonRepository;

    public NaturalPersonClientSubService(NaturalPersonRepository naturalPersonRepository) {
        this.naturalPersonRepository = naturalPersonRepository;
    }

    @Override
    public Client createClient(ClientCreateDTO clientCreateDTO) {
        Client client = new Client();
        NaturalPerson naturalPerson = new NaturalPerson();

        naturalPerson.setCpf(clientCreateDTO.getCpf());
        naturalPerson.setName(clientCreateDTO.getName());
        naturalPerson.setMcc(clientCreateDTO.getMcc());
        naturalPerson.setEmail(clientCreateDTO.getEmail());

        client.setNaturalPerson(naturalPerson);

        naturalPersonRepository.save(naturalPerson);

        return client;
    }

    @Override
    public Boolean clientExists(ClientCreateDTO clientCreateDTO) {
        return naturalPersonRepository.existsByCpf(clientCreateDTO.getCpf());
    }

}
