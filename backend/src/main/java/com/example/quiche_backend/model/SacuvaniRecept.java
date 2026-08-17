package com.example.quiche_backend.model;

import jakarta.persistence.*;

/*
    @author: mihdjo
*/

@Entity
@Table(name = "SacuvaniRecept")
@IdClass(SacuvaniReceptId.class)
public class SacuvaniRecept {

    @Id
    @Column(name = "idKorisnik")
    private Integer idKorisnik;

    @Id
    @Column(name = "idRecept")
    private Integer idRecept;

    public SacuvaniRecept() {
    }

    public SacuvaniRecept(Integer idKorisnik, Integer idRecept) {
        this.idKorisnik = idKorisnik;
        this.idRecept = idRecept;
    }

    public Integer getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Integer idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public Integer getIdRecept() {
        return idRecept;
    }

    public void setIdRecept(Integer idRecept) {
        this.idRecept = idRecept;
    }
}