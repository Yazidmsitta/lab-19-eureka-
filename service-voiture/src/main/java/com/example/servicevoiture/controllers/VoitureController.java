package com.example.servicevoiture.controllers;

import com.example.servicevoiture.entities.Voiture;
import com.example.servicevoiture.models.Client;
import com.example.servicevoiture.repositories.VoitureRepository;
import com.example.servicevoiture.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VoitureController {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private ClientService clientService;

    @GetMapping("/actuator/info")
    public java.util.Map<String, Object> info() {
        return java.util.Map.of("service", "SERVICE-VOITURE", "status", "UP", "endpoint", "/voitures");
    }

    @GetMapping("/")
    public List<Voiture> home() {
        return findAll();
    }

    @GetMapping("/voitures")
    public List<Voiture> findAll() {
        List<Voiture> voitures = voitureRepository.findAll();
        for (Voiture voiture : voitures) {
            try {
                Client client = clientService.clientById(voiture.getId_client());
                voiture.setClient(client);
            } catch (Exception e) {
                // En cas d'indisponibilité temporaire du service client
                voiture.setClient(null);
            }
        }
        return voitures;
    }

    @GetMapping("/voitures/{id}")
    public ResponseEntity<Voiture> findById(@PathVariable Long id) {
        return voitureRepository.findById(id)
                .map(voiture -> {
                    try {
                        Client client = clientService.clientById(voiture.getId_client());
                        voiture.setClient(client);
                    } catch (Exception e) {
                        voiture.setClient(null);
                    }
                    return ResponseEntity.ok(voiture);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/voitures/client/{clientId}")
    public List<Voiture> findByClientId(@PathVariable Long clientId) {
        List<Voiture> voitures = voitureRepository.findByClientId(clientId);
        try {
            Client client = clientService.clientById(clientId);
            voitures.forEach(v -> v.setClient(client));
        } catch (Exception ignored) {
        }
        return voitures;
    }

}

