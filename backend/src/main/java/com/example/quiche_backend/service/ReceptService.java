package com.example.quiche_backend.service;

import com.example.quiche_backend.model.Recept;
import com.example.quiche_backend.repository.ReceptRepository;
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

    public ReceptService(ReceptRepository receptRepository) {
        this.receptRepository = receptRepository;
    }

    public List<Recept> getAllRecepti() {
        return receptRepository.findAll();
    }

    public Recept getReceptById(Integer id) {
        return receptRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Recept nije pronađen."
                        )
                );
    }

    public Recept createRecept(Recept recept) {

        recept.setIdRecept(null);
        recept.setDatumKreiranja(LocalDateTime.now());

        return receptRepository.save(recept);
    }

    public Recept updateRecept(Integer id, Recept noviPodaci) {

        Recept recept = getReceptById(id);

        LocalDateTime granicaIzmene =
                recept.getDatumKreiranja().plusMinutes(30);

        if (LocalDateTime.now().isAfter(granicaIzmene)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Recept se može menjati samo 30 minuta nakon kreiranja."
            );
        }

        recept.setNaziv(noviPodaci.getNaziv());
        recept.setOpis(noviPodaci.getOpis());
        recept.setNapomena(noviPodaci.getNapomena());
        recept.setIdTipKuhinje(noviPodaci.getIdTipKuhinje());

        return receptRepository.save(recept);
    }

    public void deleteRecept(Integer id) {

        Recept recept = getReceptById(id);

        receptRepository.delete(recept);
    }
}