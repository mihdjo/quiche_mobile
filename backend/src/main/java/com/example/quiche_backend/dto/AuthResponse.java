package com.example.quiche_backend.dto;

/*
    @author: mihdjo
*/

public class AuthResponse {

    private String token;
    private Integer idKorisnik;
    private String username;

    public AuthResponse(
            String token,
            Integer idKorisnik,
            String username) {

        this.token = token;
        this.idKorisnik = idKorisnik;
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public Integer getIdKorisnik() {
        return idKorisnik;
    }

    public String getUsername() {
        return username;
    }
}