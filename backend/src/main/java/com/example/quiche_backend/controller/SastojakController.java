package com.example.quiche_backend.controller;

import com.example.quiche_backend.model.Sastojak;
import com.example.quiche_backend.service.SastojakService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class SastojakController {

    private final SastojakService sastojakService;

    public SastojakController(SastojakService sastojakService) {
        this.sastojakService = sastojakService;
    }

    @GetMapping
    public List<Sastojak> getAllSastojci() {
        return sastojakService.getAllSastojci();
    }

    @GetMapping("/{id}")
    public Sastojak getSastojakById(@PathVariable Integer id) {
        return sastojakService.getSastojakById(id);
    }

    @PostMapping
    public ResponseEntity<Sastojak> createSastojak(
            @RequestBody Sastojak sastojak) {

        Sastojak noviSastojak =
                sastojakService.createSastojak(sastojak);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(noviSastojak);
    }

    @PutMapping("/{id}")
    public Sastojak updateSastojak(
            @PathVariable Integer id,
            @RequestBody Sastojak sastojak) {

        return sastojakService.updateSastojak(id, sastojak);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSastojak(
            @PathVariable Integer id) {

        sastojakService.deleteSastojak(id);

        return ResponseEntity.noContent().build();
    }
}