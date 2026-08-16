package com.example.quiche_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/*
    @author: mihdjo
*/

@Entity
@Table(name = "recept")
public class Recept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRecept")
    private Integer idRecept;

    @Column(name = "naziv", nullable = false)
    private String naziv;

    @Column(name = "opis", nullable = false)
    private String opis;

    @Column(name = "napomena")
    private String napomena;

    @Column(name = "datumKreiranja", nullable = false)
    private LocalDateTime datumKreiranja;

    @Column(name = "idTipKuhinje", nullable = false)
    private Integer idTipKuhinje;

    @Column(name = "idKorisnik", nullable = false)
    private Integer idKorisnik;

    public Recept() {
    }

    public Integer getIdRecept() {
        return idRecept;
    }

    public void setIdRecept(Integer idRecept) {
        this.idRecept = idRecept;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public LocalDateTime getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(LocalDateTime datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public Integer getIdTipKuhinje() {
        return idTipKuhinje;
    }

    public void setIdTipKuhinje(Integer idTipKuhinje) {
        this.idTipKuhinje = idTipKuhinje;
    }

    public Integer getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Integer idKorisnik) {
        this.idKorisnik = idKorisnik;
    }
}