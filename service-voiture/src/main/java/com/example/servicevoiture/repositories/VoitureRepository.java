package com.example.servicevoiture.repositories;

import com.example.servicevoiture.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    @Query("SELECT v FROM Voiture v WHERE v.id_client = :idClient")
    List<Voiture> findByClientId(@Param("idClient") Long idClient);

}
