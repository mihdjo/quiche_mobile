package com.example.quiche_backend.dto;

import com.example.quiche_backend.model.Korisnik;
import java.time.LocalDate;

/*
    @author: mihdjo
*/

public class KorisnikResponse {

    private Integer idKorisnik;
    private String ime;
    private String prezime;
    private String username;
    private LocalDate datumRodjenja;

    public KorisnikResponse(Korisnik korisnik) {
        this.idKorisnik = korisnik.getIdKorisnik();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.username = korisnik.getUsername();
        this.datumRodjenja = korisnik.getDatumRodjenja();
    }

    public Integer getIdKorisnik() {
        return idKorisnik;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }
}