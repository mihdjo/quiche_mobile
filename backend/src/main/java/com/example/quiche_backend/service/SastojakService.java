package com.example.quiche_backend.service;

import com.example.quiche_backend.model.Sastojak;
import com.example.quiche_backend.repository.SastojakRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.quiche_backend.dto.SastojakRequest;
import java.util.List;

/*
    @author: mihdjo
*/

@Service
public class SastojakService {

    private final SastojakRepository sastojakRepository;

    public SastojakService(SastojakRepository sastojakRepository) {
        this.sastojakRepository = sastojakRepository;
    }

    public List<Sastojak> getAllSastojci() {
        return sastojakRepository.findAll();
    }

    public Sastojak getSastojakById(Integer id) {
        return sastojakRepository.findById(id)
                .orElseThrow(()
                        -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Sastojak nije pronađen."
                )
                );
    }

    public Sastojak createSastojak(SastojakRequest request) {

        Sastojak sastojak = new Sastojak();
        sastojak.setNaziv(request.getNaziv());

        return sastojakRepository.save(sastojak);
    }

    public Sastojak updateSastojak(
            Integer id,
            SastojakRequest request) {

        Sastojak sastojak = getSastojakById(id);

        sastojak.setNaziv(request.getNaziv());

        return sastojakRepository.save(sastojak);
    }

    public void deleteSastojak(Integer id) {

        Sastojak sastojak = getSastojakById(id);

        sastojakRepository.delete(sastojak);
    }
}
