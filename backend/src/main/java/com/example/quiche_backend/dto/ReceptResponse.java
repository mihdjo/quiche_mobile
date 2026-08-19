package com.example.quiche_backend.dto;

import java.time.LocalDateTime;
import java.util.List;

/*
    @author: mihdjo
*/

public class ReceptResponse {

    private Integer idRecept;
    private String naziv;
    private String opis;
    private String napomena;
    private LocalDateTime datumKreiranja;

    private Integer idTipKuhinje;
    private String tipKuhinje;

    private Integer idKorisnik;
    private String autorUsername;

    private List<ReceptSastojakResponse> sastojci;

    public ReceptResponse(
            Integer idRecept,
            String naziv,
            String opis,
            String napomena,
            LocalDateTime datumKreiranja,
            Integer idTipKuhinje,
            String tipKuhinje,
            Integer idKorisnik,
            String autorUsername,
            List<ReceptSastojakResponse> sastojci) {

        this.idRecept = idRecept;
        this.naziv = naziv;
        this.opis = opis;
        this.napomena = napomena;
        this.datumKreiranja = datumKreiranja;
        this.idTipKuhinje = idTipKuhinje;
        this.tipKuhinje = tipKuhinje;
        this.idKorisnik = idKorisnik;
        this.autorUsername = autorUsername;
        this.sastojci = sastojci;
    }

    public Integer getIdRecept() {
        return idRecept;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public String getNapomena() {
        return napomena;
    }

    public LocalDateTime getDatumKreiranja() {
        return datumKreiranja;
    }

    public Integer getIdTipKuhinje() {
        return idTipKuhinje;
    }

    public String getTipKuhinje() {
        return tipKuhinje;
    }

    public Integer getIdKorisnik() {
        return idKorisnik;
    }

    public String getAutorUsername() {
        return autorUsername;
    }

    public List<ReceptSastojakResponse> getSastojci() {
        return sastojci;
    }
}