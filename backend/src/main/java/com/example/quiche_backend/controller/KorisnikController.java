package com.example.quiche_backend.controller;

import com.example.quiche_backend.dto.KorisnikResponse;
import com.example.quiche_backend.model.Korisnik;
import com.example.quiche_backend.model.Recept;
import com.example.quiche_backend.service.SacuvaniReceptService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.quiche_backend.service.KorpaService;
import com.example.quiche_backend.dto.CartIngredientResponse;

/*
    @author: mihdjo
*/
@RestController
@RequestMapping("/api/users")
public class KorisnikController {

    private final SacuvaniReceptService sacuvaniReceptService;
    private final KorpaService korpaService;

    public KorisnikController(
            SacuvaniReceptService sacuvaniReceptService,
            KorpaService korpaService) {

        this.sacuvaniReceptService = sacuvaniReceptService;
        this.korpaService = korpaService;
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

    @GetMapping("/me/cart")
    public List<Recept> getKorpa(
            @AuthenticationPrincipal Korisnik korisnik) {

        return korpaService.getKorpa(korisnik);
    }

    @PostMapping("/me/cart/{recipeId}")
    public ResponseEntity<Void> dodajReceptUKorpu(
            @AuthenticationPrincipal Korisnik korisnik,
            @PathVariable Integer recipeId) {

        korpaService.dodajReceptUKorpu(
                korisnik,
                recipeId
        );

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/me/cart/{recipeId}")
    public ResponseEntity<Void> ukloniReceptIzKorpe(
            @AuthenticationPrincipal Korisnik korisnik,
            @PathVariable Integer recipeId) {

        korpaService.ukloniReceptIzKorpe(
                korisnik,
                recipeId
        );

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/me/cart")
    public ResponseEntity<Void> isprazniKorpu(
            @AuthenticationPrincipal Korisnik korisnik) {

        korpaService.isprazniKorpu(korisnik);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me/cart/ingredients")
    public List<CartIngredientResponse> getSastojciKorpe(
            @AuthenticationPrincipal Korisnik korisnik) {

        return korpaService.getSastojciKorpe(korisnik);
    }
}
