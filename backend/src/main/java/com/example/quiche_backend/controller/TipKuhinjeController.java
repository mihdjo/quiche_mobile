package com.example.quiche_backend.controller;

import com.example.quiche_backend.model.TipKuhinje;
import com.example.quiche_backend.service.TipKuhinjeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
    @author: mihdjo
*/

@RestController
@RequestMapping("/api/cuisines")
public class TipKuhinjeController {

    private final TipKuhinjeService tipKuhinjeService;

    public TipKuhinjeController(TipKuhinjeService tipKuhinjeService) {
        this.tipKuhinjeService = tipKuhinjeService;
    }

    @GetMapping
    public List<TipKuhinje> getAllTipoviKuhinje() {
        return tipKuhinjeService.getAllTipoviKuhinje();
    }

    @GetMapping("/{id}")
    public TipKuhinje getTipKuhinjeById(@PathVariable Integer id) {
        return tipKuhinjeService.getTipKuhinjeById(id);
    }
}