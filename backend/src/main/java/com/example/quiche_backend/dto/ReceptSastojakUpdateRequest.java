package com.example.quiche_backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/*
    @author: mihdjo
 */

public class ReceptSastojakUpdateRequest {

    @NotNull(message = "Količina je obavezna.")
    @DecimalMin(
            value = "0.01",
            message = "Količina mora biti veća od 0."
    )
    private BigDecimal kolicina;

    @NotBlank(message = "Jedinica mere je obavezna.")
    private String jedinicaMere;

    public ReceptSastojakUpdateRequest() {
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