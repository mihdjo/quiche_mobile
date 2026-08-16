package com.example.quiche_backend.dto;

import java.math.BigDecimal;

public class ReceptSastojakResponse {

    private Integer idSastojak;
    private String naziv;
    private BigDecimal kolicina;
    private String jedinicaMere;

    public ReceptSastojakResponse(
            Integer idSastojak,
            String naziv,
            BigDecimal kolicina,
            String jedinicaMere) {

        this.idSastojak = idSastojak;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.jedinicaMere = jedinicaMere;
    }

    public Integer getIdSastojak() {
        return idSastojak;
    }

    public String getNaziv() {
        return naziv;
    }

    public BigDecimal getKolicina() {
        return kolicina;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }
}