package com.example.quiche_backend.controller;

import com.example.quiche_backend.model.Recept;
import com.example.quiche_backend.service.ReceptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    @author: mihdjo
*/

@RestController
@RequestMapping("/api/recipes")
public class ReceptController {

    private final ReceptService receptService;

    public ReceptController(ReceptService receptService) {
        this.receptService = receptService;
    }

    @GetMapping
    public List<Recept> getAllRecepti() {
        return receptService.getAllRecepti();
    }

    @GetMapping("/{id}")
    public Recept getReceptById(@PathVariable Integer id) {
        return receptService.getReceptById(id);
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
}