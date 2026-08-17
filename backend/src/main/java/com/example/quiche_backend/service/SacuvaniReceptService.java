package com.example.quiche_backend.service;

import com.example.quiche_backend.model.Korisnik;
import com.example.quiche_backend.model.Recept;
import com.example.quiche_backend.model.SacuvaniRecept;
import com.example.quiche_backend.model.SacuvaniReceptId;
import com.example.quiche_backend.repository.ReceptRepository;
import com.example.quiche_backend.repository.SacuvaniReceptRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

/*
    @author: mihdjo
*/

@Service
public class SacuvaniReceptService {

    private final SacuvaniReceptRepository sacuvaniReceptRepository;
    private final ReceptRepository receptRepository;

    public SacuvaniReceptService(
            SacuvaniReceptRepository sacuvaniReceptRepository,
            ReceptRepository receptRepository) {

        this.sacuvaniReceptRepository = sacuvaniReceptRepository;
        this.receptRepository = receptRepository;
    }

    public List<Recept> getSacuvaniRecepti(Korisnik korisnik) {

        List<SacuvaniRecept> sacuvani =
                sacuvaniReceptRepository
                        .findByIdKorisnik(korisnik.getIdKorisnik());

        return sacuvani.stream()
                .map(sr -> receptRepository
                        .findById(sr.getIdRecept())
                        .orElseThrow())
                .toList();
    }

    public void sacuvajRecept(
            Korisnik korisnik,
            Integer idRecept) {

        if (!receptRepository.existsById(idRecept)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Recept nije pronađen."
            );
        }

        SacuvaniReceptId id =
                new SacuvaniReceptId(
                        korisnik.getIdKorisnik(),
                        idRecept
                );

        if (sacuvaniReceptRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Recept je već sačuvan."
            );
        }

        SacuvaniRecept sacuvaniRecept =
                new SacuvaniRecept(
                        korisnik.getIdKorisnik(),
                        idRecept
                );

        sacuvaniReceptRepository.save(sacuvaniRecept);
    }

    public void ukloniSacuvaniRecept(
            Korisnik korisnik,
            Integer idRecept) {

        SacuvaniReceptId id =
                new SacuvaniReceptId(
                        korisnik.getIdKorisnik(),
                        idRecept
                );

        if (!sacuvaniReceptRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Recept nije pronađen među sačuvanim receptima."
            );
        }

        sacuvaniReceptRepository.deleteById(id);
    }
}