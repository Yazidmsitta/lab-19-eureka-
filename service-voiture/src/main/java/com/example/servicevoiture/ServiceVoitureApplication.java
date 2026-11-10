package com.example.servicevoiture;

import com.example.servicevoiture.entities.Voiture;
import com.example.servicevoiture.repositories.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceVoitureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVoitureApplication.class, args);
    }

    @Bean
    CommandLineRunner initialiserBaseVoitures(VoitureRepository voitureRepository) {
        return args -> {
            voitureRepository.save(new Voiture(null, "Toyota", "A 12345", "Yaris", 1L, null));
            voitureRepository.save(new Voiture(null, "Renault", "B 67890", "Clio", 1L, null));
            voitureRepository.save(new Voiture(null, "Peugeot", "C 11223", "208", 2L, null));
        };
    }

}

