package com.adacielochallenge.prospect.service;

import com.adacielochallenge.prospect.dto.ClientCreateDTO;

public interface ClientSubService {
    void createClient(ClientCreateDTO clientCreateDTO);

    Boolean clientExists(ClientCreateDTO clientCreateDTO);
}
