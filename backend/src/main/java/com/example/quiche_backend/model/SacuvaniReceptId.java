package com.example.quiche_backend.model;

import java.io.Serializable;
import java.util.Objects;

/*
    @author: mihdjo
*/

public class SacuvaniReceptId implements Serializable {

    private Integer idKorisnik;
    private Integer idRecept;

    public SacuvaniReceptId() {
    }

    public SacuvaniReceptId(Integer idKorisnik, Integer idRecept) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SacuvaniReceptId)) {
            return false;
        }

        SacuvaniReceptId that = (SacuvaniReceptId) o;

        return Objects.equals(idKorisnik, that.idKorisnik)
                && Objects.equals(idRecept, that.idRecept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKorisnik, idRecept);
    }
}