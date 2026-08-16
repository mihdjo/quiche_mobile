package com.example.quiche_backend.model;

import java.io.Serializable;
import java.util.Objects;

/*
    @author: mihdjo
*/

public class ReceptSastojakId implements Serializable {

    private Integer idRecept;
    private Integer idSastojak;

    public ReceptSastojakId() {
    }

    public ReceptSastojakId(Integer idRecept, Integer idSastojak) {
        this.idRecept = idRecept;
        this.idSastojak = idSastojak;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ReceptSastojakId)) {
            return false;
        }

        ReceptSastojakId that = (ReceptSastojakId) o;

        return Objects.equals(idRecept, that.idRecept)
                && Objects.equals(idSastojak, that.idSastojak);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRecept, idSastojak);
    }
}