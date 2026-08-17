package com.example.quiche_backend.model;

import java.io.Serializable;
import java.util.Objects;

/*
    @author: mihdjo
*/

public class KorpaId implements Serializable {

    private Integer idKorisnik;
    private Integer idRecept;

    public KorpaId() {
    }

    public KorpaId(Integer idKorisnik, Integer idRecept) {
        this.idKorisnik = idKorisnik;
        this.idRecept = idRecept;
    }

    public Integer getIdKorisnik() {
        return idKorisnik;
    }

    public Integer getIdRecept() {
        return idRecept;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KorpaId)) return false;

        KorpaId korpaId = (KorpaId) o;

        return Objects.equals(idKorisnik, korpaId.idKorisnik)
                && Objects.equals(idRecept, korpaId.idRecept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKorisnik, idRecept);
    }
}