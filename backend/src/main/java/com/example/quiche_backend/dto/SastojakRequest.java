package com.example.quiche_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
    @author: mihdjo
 */

public class SastojakRequest {

    @NotBlank(message = "Naziv sastojka je obavezan.")
    @Size(max = 100,
            message = "Naziv sastojka može imati najviše 100 karaktera.")
    private String naziv;

    public SastojakRequest() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}