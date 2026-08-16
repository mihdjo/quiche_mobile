package com.example.quiche_backend.dto;

import java.math.BigDecimal;

/*
    @author: mihdjo
*/

public class ReceptSastojakRequest {

    private Integer idSastojak;
    private BigDecimal kolicina;
    private String jedinicaMere;

    public ReceptSastojakRequest() {
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