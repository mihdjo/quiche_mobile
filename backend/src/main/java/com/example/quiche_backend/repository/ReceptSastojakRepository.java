package com.example.quiche_backend.repository;

import com.example.quiche_backend.model.ReceptSastojak;
import com.example.quiche_backend.model.ReceptSastojakId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    @author: mihdjo
*/

public interface ReceptSastojakRepository
        extends JpaRepository<ReceptSastojak, ReceptSastojakId> {

    List<ReceptSastojak> findByIdRecept(Integer idRecept);
}