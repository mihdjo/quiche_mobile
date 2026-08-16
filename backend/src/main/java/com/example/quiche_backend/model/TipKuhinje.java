package com.example.quiche_backend.model;

import jakarta.persistence.*;

/*
    @author: mihdjo
*/

@Entity
@Table(name = "TipKuhinje")
public class TipKuhinje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipKuhinje")
    private Integer idTipKuhinje;

    @Column(name = "tip", nullable = false, unique = true)
    private String tip;

    public TipKuhinje() {
    }

    public Integer getIdTipKuhinje() {
        return idTipKuhinje;
    }

    public void setIdTipKuhinje(Integer idTipKuhinje) {
        this.idTipKuhinje = idTipKuhinje;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}