package com.example.quiche_backend.controller;

import com.example.quiche_backend.dto.KorisnikResponse;
import com.example.quiche_backend.model.Korisnik;
import com.example.quiche_backend.model.Recept;
import com.example.quiche_backend.service.SacuvaniReceptService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
    @author: mihdjo
*/

@RestController
@RequestMapping("/api/users")
public class KorisnikController {

    private final SacuvaniReceptService sacuvaniReceptService;

    public KorisnikController(
            SacuvaniReceptService sacuvaniReceptService) {

        this.sacuvaniReceptService = sacuvaniReceptService;
    }

    @GetMapping("/me")
    public KorisnikResponse getCurrentUser(
            @AuthenticationPrincipal Korisnik korisnik) {

        return new KorisnikResponse(korisnik);
    }

    @GetMapping("/me/saved-recipes")
    public List<Recept> getSacuvaniRecepti(
            @AuthenticationPrincipal Korisnik korisnik) {

        return sacuvaniReceptService
                .getSacuvaniRecepti(korisnik);
    }

    @PostMapping("/me/saved-recipes/{recipeId}")
    public ResponseEntity<Void> sacuvajRecept(
            @AuthenticationPrincipal Korisnik korisnik,
            @PathVariable Integer recipeId) {

        sacuvaniReceptService
                .sacuvajRecept(korisnik, recipeId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/me/saved-recipes/{recipeId}")
    public ResponseEntity<Void> ukloniSacuvaniRecept(
            @AuthenticationPrincipal Korisnik korisnik,
            @PathVariable Integer recipeId) {

        sacuvaniReceptService
                .ukloniSacuvaniRecept(korisnik, recipeId);

        return ResponseEntity.noContent().build();
    }
}