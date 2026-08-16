package com.example.quiche_backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/*
    @author: mihdjo
*/

@Entity
@Table(name = "ReceptSastojak")
@IdClass(ReceptSastojakId.class)
public class ReceptSastojak {

    @Id
    @Column(name = "idRecept")
    private Integer idRecept;

    @Id
    @Column(name = "idSastojak")
    private Integer idSastojak;

    @Column(name = "kolicina", nullable = false)
    private BigDecimal kolicina;

    @Column(name = "jedinicaMere", nullable = false)
    private String jedinicaMere;

    public ReceptSastojak() {
    }

    public Integer getIdRecept() {
        return idRecept;
    }

    public void setIdRecept(Integer idRecept) {
        this.idRecept = idRecept;
    }

    public Integer getIdSastojak() {
        return idSastojak;
    }

    public void setIdSastojak(Integer idSastojak) {
        this.idSastojak = idSastojak;
    }

    public BigDecimal getKolicina() {
        return kolicina;
    }

    public void setKolicina(BigDecimal kolicina) {
        this.kolicina = kolicina;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(String jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }
}