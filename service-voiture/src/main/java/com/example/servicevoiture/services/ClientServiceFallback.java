package com.example.servicevoiture.services;

import com.example.servicevoiture.models.Client;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ClientServiceFallback implements ClientService {

    @Override
    public Client clientById(Long id) {
        return new Client(id, "Client indisponible (Mode dégradé)", 0f);
    }

    @Override
    public List<Client> allClients() {
        return Collections.emptyList();
    }

}

