package com.example.quiche_backend.repository;

import com.example.quiche_backend.model.Sastojak;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    @author: mihdjo
*/

public interface SastojakRepository
        extends JpaRepository<Sastojak, Integer> {
}