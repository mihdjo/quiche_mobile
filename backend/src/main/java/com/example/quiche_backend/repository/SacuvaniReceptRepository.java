package com.example.quiche_backend.repository;

import com.example.quiche_backend.model.SacuvaniRecept;
import com.example.quiche_backend.model.SacuvaniReceptId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/*
    @author: mihdjo
*/

public interface SacuvaniReceptRepository
        extends JpaRepository<SacuvaniRecept, SacuvaniReceptId> {

    List<SacuvaniRecept> findByIdKorisnik(Integer idKorisnik);
}