package com.adacielochallenge.prospect.service;

import com.adacielochallenge.prospect.dto.ClientCreateDTO;
import com.adacielochallenge.prospect.model.Client;

public interface ClientSubService {
    Client createClient(ClientCreateDTO clientCreateDTO);

    Boolean isClientCreated(ClientCreateDTO clientCreateDTO);
}
