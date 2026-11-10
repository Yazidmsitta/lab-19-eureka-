package com.example.servicevoiture.services;

import com.example.servicevoiture.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "SERVICE-CLIENT")
public interface ClientService {

    @GetMapping("/clients/{id}")
    Client clientById(@PathVariable("id") Long id);

    @GetMapping("/clients")
    List<Client> allClients();

}

