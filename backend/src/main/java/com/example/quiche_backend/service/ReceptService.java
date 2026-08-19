package com.example.quiche_backend.service;

import com.example.quiche_backend.dto.ReceptRequest;
import com.example.quiche_backend.dto.ReceptResponse;
import com.example.quiche_backend.dto.ReceptSastojakResponse;
import com.example.quiche_backend.model.Korisnik;
import com.example.quiche_backend.model.Recept;
import com.example.quiche_backend.model.ReceptSastojak;
import com.example.quiche_backend.model.Sastojak;
import com.example.quiche_backend.model.TipKuhinje;
import com.example.quiche_backend.repository.KorisnikRepository;
import com.example.quiche_backend.repository.ReceptRepository;
import com.example.quiche_backend.repository.ReceptSastojakRepository;
import com.example.quiche_backend.repository.SastojakRepository;
import com.example.quiche_backend.repository.TipKuhinjeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

/*
    @author: mihdjo
 */


@Service
public class ReceptService {

    private final ReceptRepository receptRepository;
    private final TipKuhinjeRepository tipKuhinjeRepository;
    private final KorisnikRepository korisnikRepository;
    private final ReceptSastojakRepository receptSastojakRepository;
    private final SastojakRepository sastojakRepository;

    public ReceptService(
            ReceptRepository receptRepository,
            TipKuhinjeRepository tipKuhinjeRepository,
            KorisnikRepository korisnikRepository,
            ReceptSastojakRepository receptSastojakRepository,
            SastojakRepository sastojakRepository) {

        this.receptRepository = receptRepository;
        this.tipKuhinjeRepository = tipKuhinjeRepository;
        this.korisnikRepository = korisnikRepository;
        this.receptSastojakRepository = receptSastojakRepository;
        this.sastojakRepository = sastojakRepository;
    }

    public List<Recept> getRecepti(
            String name,
            Integer cuisine) {

        boolean imaNaziv
                = name != null && !name.isBlank();

        boolean imaKuhinju
                = cuisine != null;

        if (imaNaziv && imaKuhinju) {
            return receptRepository
                    .findByNazivContainingIgnoreCaseAndIdTipKuhinje(
                            name.trim(),
                            cuisine
                    );
        }

        if (imaNaziv) {
            return receptRepository
                    .findByNazivContainingIgnoreCase(
                            name.trim()
                    );
        }

        if (imaKuhinju) {
            return receptRepository
                    .findByIdTipKuhinje(cuisine);
        }

        return receptRepository.findAll();
    }

    public Recept getReceptById(Integer id) {
        return receptRepository.findById(id)
                .orElseThrow(()
                        ->          new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Recept nije pronađen."
                        )
                );
    }

    public ReceptResponse getReceptDetailsById(Integer id) {

        Recept recept = getReceptById(id);

        TipKuhinje tipKuhinje = tipKuhinjeRepository
                .findById(recept.getIdTipKuhinje())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Tip kuhinje nije pronađen."
                        )
                );

        Korisnik autor = korisnikRepository
                .findById(recept.getIdKorisnik())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Autor recepta nije pronađen."
                        )
                );

        List<ReceptSastojak> veze =
                receptSastojakRepository.findByIdRecept(id);

        List<ReceptSastojakResponse> sastojci =
                veze.stream()
                        .map(veza -> {

                            Sastojak sastojak = sastojakRepository
                                    .findById(veza.getIdSastojak())
                                    .orElseThrow(() ->
                                            new ResponseStatusException(
                                                    HttpStatus.NOT_FOUND,
                                                    "Sastojak nije pronađen."
                                            )
                                    );

                            return new ReceptSastojakResponse(
                                    sastojak.getIdSastojak(),
                                    sastojak.getNaziv(),
                                    veza.getKolicina(),
                                    veza.getJedinicaMere()
                            );
                        })
                        .toList();

        return new ReceptResponse(
                recept.getIdRecept(),
                recept.getNaziv(),
                recept.getOpis(),
                recept.getNapomena(),
                recept.getDatumKreiranja(),
                tipKuhinje.getIdTipKuhinje(),
                tipKuhinje.getTip(),
                autor.getIdKorisnik(),
                autor.getUsername(),
                sastojci
        );
    }

    public Recept createRecept(
            ReceptRequest request,
            Korisnik korisnik) {

        // Provera da tip kuhinje postoji
        if (!tipKuhinjeRepository.existsById(
                request.getIdTipKuhinje())) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Tip kuhinje nije pronađen."
            );
        }

        Recept recept = new Recept();

        recept.setNaziv(request.getNaziv());
        recept.setOpis(request.getOpis());
        recept.setNapomena(request.getNapomena());
        recept.setIdTipKuhinje(request.getIdTipKuhinje());

        // Autor dolazi iz JWT-a
        recept.setIdKorisnik(korisnik.getIdKorisnik());

        recept.setDatumKreiranja(LocalDateTime.now());

        return receptRepository.save(recept);
    }

    public Recept updateRecept(
            Integer id,
            ReceptRequest request) {

        Recept recept = getReceptById(id);

        LocalDateTime granicaIzmene
                = recept.getDatumKreiranja().plusMinutes(30);

        if (LocalDateTime.now().isAfter(granicaIzmene)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Recept se može menjati samo 30 minuta nakon kreiranja."
            );
        }

        if (!tipKuhinjeRepository.existsById(
                request.getIdTipKuhinje())) {

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Tip kuhinje nije pronađen."
            );
        }

        recept.setNaziv(request.getNaziv());
        recept.setOpis(request.getOpis());
        recept.setNapomena(request.getNapomena());
        recept.setIdTipKuhinje(request.getIdTipKuhinje());

        return receptRepository.save(recept);
    }

    public void deleteRecept(Integer id) {

        Recept recept = getReceptById(id);

        receptRepository.delete(recept);
    }
}