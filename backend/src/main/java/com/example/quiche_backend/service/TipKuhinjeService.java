package com.example.quiche_backend.service;

import com.example.quiche_backend.model.TipKuhinje;
import com.example.quiche_backend.repository.TipKuhinjeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/*
    @author: mihdjo
*/

@Service
public class TipKuhinjeService {

    private final TipKuhinjeRepository tipKuhinjeRepository;

    public TipKuhinjeService(TipKuhinjeRepository tipKuhinjeRepository) {
        this.tipKuhinjeRepository = tipKuhinjeRepository;
    }

    public List<TipKuhinje> getAllTipoviKuhinje() {
        return tipKuhinjeRepository.findAll();
    }

    public TipKuhinje getTipKuhinjeById(Integer id) {
        return tipKuhinjeRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Tip kuhinje nije pronađen."
                        )
                );
    }
}