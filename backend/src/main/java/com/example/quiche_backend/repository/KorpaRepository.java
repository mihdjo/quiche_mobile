package com.example.quiche_backend.repository;

import com.example.quiche_backend.model.Korpa;
import com.example.quiche_backend.model.KorpaId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/*
    @author: mihdjo
*/

public interface KorpaRepository
        extends JpaRepository<Korpa, KorpaId> {

    List<Korpa> findByIdKorisnik(Integer idKorisnik);

    void deleteByIdKorisnik(Integer idKorisnik);
}