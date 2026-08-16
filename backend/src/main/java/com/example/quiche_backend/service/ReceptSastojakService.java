package com.example.quiche_backend.service;

import com.example.quiche_backend.dto.ReceptSastojakResponse;
import com.example.quiche_backend.model.ReceptSastojak;
import com.example.quiche_backend.model.Sastojak;
import com.example.quiche_backend.repository.ReceptSastojakRepository;
import com.example.quiche_backend.repository.SastojakRepository;
import org.springframework.stereotype.Service;
import com.example.quiche_backend.dto.ReceptSastojakRequest;
import com.example.quiche_backend.model.ReceptSastojakId;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReceptSastojakService {

    private final ReceptSastojakRepository receptSastojakRepository;
    private final SastojakRepository sastojakRepository;
    private final ReceptService receptService;

    public ReceptSastojakService(
            ReceptSastojakRepository receptSastojakRepository,
            SastojakRepository sastojakRepository,
            ReceptService receptService) {

        this.receptSastojakRepository = receptSastojakRepository;
        this.sastojakRepository = sastojakRepository;
        this.receptService = receptService;
    }

    public List<ReceptSastojakResponse> getSastojciZaRecept(
            Integer idRecept) {

        // Provera da recept stvarno postoji
        receptService.getReceptById(idRecept);

        List<ReceptSastojak> veze =
                receptSastojakRepository.findByIdRecept(idRecept);

        return veze.stream()
                .map(veza -> {

                    Sastojak sastojak =
                            sastojakRepository
                                    .findById(veza.getIdSastojak())
                                    .orElseThrow();

                    return new ReceptSastojakResponse(
                            sastojak.getIdSastojak(),
                            sastojak.getNaziv(),
                            veza.getKolicina(),
                            veza.getJedinicaMere()
                    );
                })
                .toList();
    }

    public ReceptSastojakResponse dodajSastojakURecept(
            Integer idRecept,
            ReceptSastojakRequest request) {

        // Provera da recept postoji
        receptService.getReceptById(idRecept);

        // Provera da sastojak postoji
        Sastojak sastojak = sastojakRepository
                .findById(request.getIdSastojak())
                .orElseThrow(()
                        -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Sastojak nije pronađen."
                )
                );

        ReceptSastojakId id
                = new ReceptSastojakId(idRecept, request.getIdSastojak());

        if (receptSastojakRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Sastojak je već dodat u recept."
            );
        }

        ReceptSastojak veza = new ReceptSastojak();

        veza.setIdRecept(idRecept);
        veza.setIdSastojak(request.getIdSastojak());
        veza.setKolicina(request.getKolicina());
        veza.setJedinicaMere(request.getJedinicaMere());

        ReceptSastojak sacuvanaVeza
                = receptSastojakRepository.save(veza);

        return new ReceptSastojakResponse(
                sastojak.getIdSastojak(),
                sastojak.getNaziv(),
                sacuvanaVeza.getKolicina(),
                sacuvanaVeza.getJedinicaMere()
        );
    }

    public ReceptSastojakResponse izmeniSastojakUReceptu(
            Integer idRecept,
            Integer idSastojak,
            ReceptSastojakRequest request) {

        receptService.getReceptById(idRecept);

        ReceptSastojakId id
                = new ReceptSastojakId(idRecept, idSastojak);

        ReceptSastojak veza = receptSastojakRepository
                .findById(id)
                .orElseThrow(()
                        -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Sastojak nije pronađen u receptu."
                )
                );

        Sastojak sastojak = sastojakRepository
                .findById(idSastojak)
                .orElseThrow(()
                        -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Sastojak nije pronađen."
                )
                );

        veza.setKolicina(request.getKolicina());
        veza.setJedinicaMere(request.getJedinicaMere());

        ReceptSastojak sacuvanaVeza
                = receptSastojakRepository.save(veza);

        return new ReceptSastojakResponse(
                sastojak.getIdSastojak(),
                sastojak.getNaziv(),
                sacuvanaVeza.getKolicina(),
                sacuvanaVeza.getJedinicaMere()
        );
    }

    public void obrisiSastojakIzRecepta(
            Integer idRecept,
            Integer idSastojak) {

        receptService.getReceptById(idRecept);

        ReceptSastojakId id
                = new ReceptSastojakId(idRecept, idSastojak);

        ReceptSastojak veza = receptSastojakRepository
                .findById(id)
                .orElseThrow(()
                        -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Sastojak nije pronađen u receptu."
                )
                );

        receptSastojakRepository.delete(veza);
    }
}
