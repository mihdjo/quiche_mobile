package com.example.quiche_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/*
    @author: mihdjo
 */

public class ReceptRequest {

    @NotBlank(message = "Naziv recepta je obavezan.")
    @Size(max = 150,
            message = "Naziv recepta može imati najviše 150 karaktera.")
    private String naziv;

    @NotBlank(message = "Opis recepta je obavezan.")
    private String opis;

    private String napomena;

    @NotNull(message = "Tip kuhinje je obavezan.")
    private Integer idTipKuhinje;

    public ReceptRequest() {
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

    public Integer getIdTipKuhinje() {
        return idTipKuhinje;
    }

    public void setIdTipKuhinje(Integer idTipKuhinje) {
        this.idTipKuhinje = idTipKuhinje;
    }
}