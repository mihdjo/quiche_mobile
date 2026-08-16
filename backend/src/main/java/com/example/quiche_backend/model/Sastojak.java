package com.example.quiche_backend.model;

import jakarta.persistence.*;

/*
    @author: mihdjo
*/

@Entity
@Table(name = "Sastojak")
public class Sastojak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSastojak")
    private Integer idSastojak;

    @Column(name = "naziv", nullable = false, unique = true)
    private String naziv;

    public Sastojak() {
    }

    public Integer getIdSastojak() {
        return idSastojak;
    }

    public void setIdSastojak(Integer idSastojak) {
        this.idSastojak = idSastojak;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}