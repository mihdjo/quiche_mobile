package com.example.quiche_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/*
    @author: mihdjo
*/

public class RegisterRequest {

    @NotBlank(message = "Ime je obavezno.")
    @Size(max = 100, message = "Ime može imati najviše 100 karaktera.")
    private String ime;

    @NotBlank(message = "Prezime je obavezno.")
    @Size(max = 100, message = "Prezime može imati najviše 100 karaktera.")
    private String prezime;

    @NotBlank(message = "Username je obavezan.")
    @Size(min = 3, max = 100,
            message = "Username mora imati između 3 i 100 karaktera.")
    private String username;

    @NotBlank(message = "Password je obavezan.")
    @Size(min = 6,
            message = "Password mora imati najmanje 6 karaktera.")
    private String password;

    @NotNull(message = "Datum rođenja je obavezan.")
    @Past(message = "Datum rođenja mora biti u prošlosti.")
    private LocalDate datumRodjenja;

    public RegisterRequest() {
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}