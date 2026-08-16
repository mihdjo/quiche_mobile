package com.example.quiche_backend.controller;

import com.example.quiche_backend.dto.ReceptSastojakResponse;
import com.example.quiche_backend.model.Recept;
import com.example.quiche_backend.service.ReceptSastojakService;
import com.example.quiche_backend.service.ReceptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.quiche_backend.dto.ReceptSastojakRequest;

import java.util.List;

/*
    @author: mihdjo
 */
@RestController
@RequestMapping("/api/recipes")
public class ReceptController {

    private final ReceptService receptService;
    private final ReceptSastojakService receptSastojakService;

    public ReceptController(
            ReceptService receptService,
            ReceptSastojakService receptSastojakService) {

        this.receptService = receptService;
        this.receptSastojakService = receptSastojakService;
    }

    @GetMapping
    public List<Recept> getAllRecepti() {
        return receptService.getAllRecepti();
    }

    @GetMapping("/{id}")
    public Recept getReceptById(@PathVariable Integer id) {
        return receptService.getReceptById(id);
    }

    @GetMapping("/{id}/ingredients")
    public List<ReceptSastojakResponse> getSastojciZaRecept(
            @PathVariable Integer id) {

        return receptSastojakService.getSastojciZaRecept(id);
    }

    @PostMapping
    public ResponseEntity<Recept> createRecept(
            @RequestBody Recept recept) {

        Recept noviRecept = receptService.createRecept(recept);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(noviRecept);
    }

    @PutMapping("/{id}")
    public Recept updateRecept(
            @PathVariable Integer id,
            @RequestBody Recept recept) {

        return receptService.updateRecept(id, recept);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecept(
            @PathVariable Integer id) {

        receptService.deleteRecept(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/ingredients")
    public ResponseEntity<ReceptSastojakResponse> dodajSastojakURecept(
            @PathVariable Integer id,
            @RequestBody ReceptSastojakRequest request) {

        ReceptSastojakResponse response
                = receptSastojakService.dodajSastojakURecept(id, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}/ingredients/{ingredientId}")
    public ReceptSastojakResponse izmeniSastojakUReceptu(
            @PathVariable Integer id,
            @PathVariable Integer ingredientId,
            @RequestBody ReceptSastojakRequest request) {

        return receptSastojakService
                .izmeniSastojakUReceptu(id, ingredientId, request);
    }

    @DeleteMapping("/{id}/ingredients/{ingredientId}")
    public ResponseEntity<Void> obrisiSastojakIzRecepta(
            @PathVariable Integer id,
            @PathVariable Integer ingredientId) {

        receptSastojakService
                .obrisiSastojakIzRecepta(id, ingredientId);

        return ResponseEntity.noContent().build();
    }
}
