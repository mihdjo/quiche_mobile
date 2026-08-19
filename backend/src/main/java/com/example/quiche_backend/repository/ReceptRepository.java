package com.example.quiche_backend.repository;

import com.example.quiche_backend.model.Recept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    @author: mihdjo
 */

public interface ReceptRepository
        extends JpaRepository<Recept, Integer> {

    List<Recept> findByNazivContainingIgnoreCase(String naziv);

    List<Recept> findByIdTipKuhinje(Integer idTipKuhinje);

    List<Recept> findByNazivContainingIgnoreCaseAndIdTipKuhinje(
            String naziv,
            Integer idTipKuhinje
    );
}