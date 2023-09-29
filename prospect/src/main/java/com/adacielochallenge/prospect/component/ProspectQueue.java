package com.adacielochallenge.prospect.component;

import java.util.List;

import org.springframework.stereotype.Component;

import com.adacielochallenge.prospect.model.Client;

import lombok.Getter;
import lombok.Setter;

@Component
public class ProspectQueue {

    @Getter
    @Setter
    private List<Client> prospects;

    public void unshift(Client client) {
        this.prospects.add(client);
    }

    public Client shift() throws IllegalStateException {
        if (this.prospects.size() == 0) {
            throw new IllegalStateException("a fila de prospects está vazia");
        }
        return this.prospects.remove(0);
    }

}