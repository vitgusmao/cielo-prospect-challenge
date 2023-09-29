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

    public void unshift(Client newProspect) {
        this.remove(newProspect.getId());
        this.prospects.add(newProspect);
    }

    public Client shift() throws IllegalStateException {
        if (this.prospects.size() == 0) {
            throw new IllegalStateException("a fila de prospects está vazia");
        }
        return this.prospects.remove(0);
    }

    public void remove(Long idToRemove) {
        boolean found = false;
        int foundIndex = -1;
        for (int i = 0; i < this.prospects.size(); i += 1) {
            Client prospect = this.prospects.get(i);
            if (prospect.getId() == idToRemove) {
                found = true;
                foundIndex = i;
                break;
            }
        }

        if (found) {
            this.prospects.remove(foundIndex);
        }
    }
}