package com.example.quiche_backend.controller;

import com.example.quiche_backend.dto.SastojakRequest;
import com.example.quiche_backend.model.Sastojak;
import com.example.quiche_backend.service.SastojakService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    @author: mihdjo
*/

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
            @Valid @RequestBody SastojakRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sastojakService.createSastojak(request));
    }

    @PutMapping("/{id}")
    public Sastojak updateSastojak(
            @PathVariable Integer id,
            @Valid @RequestBody SastojakRequest request) {

        return sastojakService.updateSastojak(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSastojak(
            @PathVariable Integer id) {

        sastojakService.deleteSastojak(id);

        return ResponseEntity.noContent().build();
    }
}
