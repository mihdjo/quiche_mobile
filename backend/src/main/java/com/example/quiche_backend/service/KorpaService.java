package com.example.quiche_backend.service;

import com.example.quiche_backend.model.Korisnik;
import com.example.quiche_backend.model.Korpa;
import com.example.quiche_backend.model.KorpaId;
import com.example.quiche_backend.model.Recept;
import com.example.quiche_backend.model.ReceptSastojak;
import com.example.quiche_backend.model.Sastojak;
import com.example.quiche_backend.repository.KorpaRepository;
import com.example.quiche_backend.repository.ReceptRepository;
import com.example.quiche_backend.repository.ReceptSastojakRepository;
import com.example.quiche_backend.repository.SastojakRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.quiche_backend.dto.CartIngredientResponse;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/*
    @author: mihdjo
 */
@Service
public class KorpaService {

    private final KorpaRepository korpaRepository;
    private final ReceptRepository receptRepository;
    private final ReceptSastojakRepository receptSastojakRepository;
    private final SastojakRepository sastojakRepository;

    public KorpaService(
            KorpaRepository korpaRepository,
            ReceptRepository receptRepository,
            ReceptSastojakRepository receptSastojakRepository,
            SastojakRepository sastojakRepository) {

        this.korpaRepository = korpaRepository;
        this.receptRepository = receptRepository;
        this.receptSastojakRepository = receptSastojakRepository;
        this.sastojakRepository = sastojakRepository;
    }

    public List<Recept> getKorpa(Korisnik korisnik) {

        List<Korpa> stavke
                = korpaRepository.findByIdKorisnik(
                        korisnik.getIdKorisnik()
                );

        return stavke.stream()
                .map(stavka -> receptRepository
                        .findById(stavka.getIdRecept())
                        .orElseThrow())
                .toList();
    }

    public void dodajReceptUKorpu(
            Korisnik korisnik,
            Integer idRecept) {

        if (!receptRepository.existsById(idRecept)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Recept nije pronađen."
            );
        }

        KorpaId id = new KorpaId(
                korisnik.getIdKorisnik(),
                idRecept
        );

        if (korpaRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Recept je već dodat u korpu."
            );
        }

        Korpa stavka = new Korpa(
                korisnik.getIdKorisnik(),
                idRecept
        );

        korpaRepository.save(stavka);
    }

    public void ukloniReceptIzKorpe(
            Korisnik korisnik,
            Integer idRecept) {

        KorpaId id = new KorpaId(
                korisnik.getIdKorisnik(),
                idRecept
        );

        if (!korpaRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Recept nije pronađen u korpi."
            );
        }

        korpaRepository.deleteById(id);
    }

    @Transactional
    public void isprazniKorpu(Korisnik korisnik) {

        korpaRepository.deleteByIdKorisnik(
                korisnik.getIdKorisnik()
        );
    }

    public List<CartIngredientResponse> getSastojciKorpe(
            Korisnik korisnik) {

        List<Korpa> stavkeKorpe
                = korpaRepository.findByIdKorisnik(
                        korisnik.getIdKorisnik()
                );

        Map<String, CartIngredientResponse> zbir
                = new LinkedHashMap<>();

        for (Korpa stavkaKorpe : stavkeKorpe) {

            List<ReceptSastojak> sastojciRecepta
                    = receptSastojakRepository
                            .findByIdRecept(stavkaKorpe.getIdRecept());

            for (ReceptSastojak veza : sastojciRecepta) {

                Sastojak sastojak = sastojakRepository
                        .findById(veza.getIdSastojak())
                        .orElseThrow();

                /*
             * Jedinica mere ulazi u ključ jer:
             *
             * Mleko 200 ml
             * Mleko 1 l
             *
             * ne možemo bez konverzije samo sabrati 200 + 1.
                 */
                String key
                        = sastojak.getIdSastojak()
                        + "-"
                        + veza.getJedinicaMere().toLowerCase();

                if (zbir.containsKey(key)) {

                    CartIngredientResponse postojeci
                            = zbir.get(key);

                    BigDecimal novaKolicina
                            = postojeci
                                    .getUkupnaKolicina()
                                    .add(veza.getKolicina());

                    zbir.put(
                            key,
                            new CartIngredientResponse(
                                    sastojak.getIdSastojak(),
                                    sastojak.getNaziv(),
                                    novaKolicina,
                                    veza.getJedinicaMere()
                            )
                    );

                } else {

                    zbir.put(
                            key,
                            new CartIngredientResponse(
                                    sastojak.getIdSastojak(),
                                    sastojak.getNaziv(),
                                    veza.getKolicina(),
                                    veza.getJedinicaMere()
                            )
                    );
                }
            }
        }

        return new ArrayList<>(zbir.values());
    }
}
