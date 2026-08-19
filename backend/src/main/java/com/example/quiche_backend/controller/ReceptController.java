package com.example.quiche_backend.controller;

import com.example.quiche_backend.dto.ReceptRequest;
import com.example.quiche_backend.dto.ReceptSastojakResponse;
import com.example.quiche_backend.model.Recept;
import com.example.quiche_backend.service.ReceptSastojakService;
import com.example.quiche_backend.service.ReceptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.quiche_backend.dto.ReceptResponse;
import com.example.quiche_backend.dto.ReceptSastojakCreateRequest;
import com.example.quiche_backend.dto.ReceptSastojakUpdateRequest;
import com.example.quiche_backend.model.Korisnik;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

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
    public List<Recept> getRecepti(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer cuisine) {

        return receptService.getRecepti(name, cuisine);
    }

    @GetMapping("/{id}")
    public ReceptResponse getReceptById(
            @PathVariable Integer id) {

        return receptService.getReceptDetailsById(id);
    }

    @GetMapping("/{id}/ingredients")
    public List<ReceptSastojakResponse> getSastojciZaRecept(
            @PathVariable Integer id) {

        return receptSastojakService.getSastojciZaRecept(id);
    }

    @PostMapping
    public ResponseEntity<Recept> createRecept(
            @AuthenticationPrincipal Korisnik korisnik,
            @Valid @RequestBody ReceptRequest request) {

        Recept noviRecept
                = receptService.createRecept(request, korisnik);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(noviRecept);
    }

    @PutMapping("/{id}")
    public Recept updateRecept(
            @PathVariable Integer id,
            @Valid @RequestBody ReceptRequest request) {

        return receptService.updateRecept(id, request);
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
            @Valid @RequestBody ReceptSastojakCreateRequest request) {

        ReceptSastojakResponse response
                = receptSastojakService
                        .dodajSastojakURecept(id, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{id}/ingredients/{ingredientId}")
    public ReceptSastojakResponse izmeniSastojakUReceptu(
            @PathVariable Integer id,
            @PathVariable Integer ingredientId,
            @Valid @RequestBody ReceptSastojakUpdateRequest request) {

        return receptSastojakService
                .izmeniSastojakUReceptu(
                        id,
                        ingredientId,
                        request
                );
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
