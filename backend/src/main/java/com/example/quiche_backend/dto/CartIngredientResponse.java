package com.example.quiche_backend.dto;

import java.math.BigDecimal;

/*
    @author: mihdjo
*/

public class CartIngredientResponse {

    private Integer idSastojak;
    private String naziv;
    private BigDecimal ukupnaKolicina;
    private String jedinicaMere;

    public CartIngredientResponse(
            Integer idSastojak,
            String naziv,
            BigDecimal ukupnaKolicina,
            String jedinicaMere) {

        this.idSastojak = idSastojak;
        this.naziv = naziv;
        this.ukupnaKolicina = ukupnaKolicina;
        this.jedinicaMere = jedinicaMere;
    }

    public Integer getIdSastojak() {
        return idSastojak;
    }

    public String getNaziv() {
        return naziv;
    }

    public BigDecimal getUkupnaKolicina() {
        return ukupnaKolicina;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }
}