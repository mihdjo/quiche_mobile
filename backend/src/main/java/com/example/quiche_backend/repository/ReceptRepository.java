package com.example.quiche_backend.repository;

import com.example.quiche_backend.model.Recept;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    @author: mihdjo
*/

public interface ReceptRepository extends JpaRepository<Recept, Integer> {
}