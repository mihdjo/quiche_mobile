package com.example.quiche_backend.repository;

import com.example.quiche_backend.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/*
    @author: mihdjo
*/

public interface KorisnikRepository
        extends JpaRepository<Korisnik, Integer> {

    Optional<Korisnik> findByUsername(String username);

    boolean existsByUsername(String username);
}