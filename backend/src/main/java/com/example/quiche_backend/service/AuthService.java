package com.example.quiche_backend.service;

import com.example.quiche_backend.dto.*;
import com.example.quiche_backend.model.Korisnik;
import com.example.quiche_backend.repository.KorisnikRepository;
import com.example.quiche_backend.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/*
    @author: mihdjo
*/

@Service
public class AuthService {

    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            KorisnikRepository korisnikRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public KorisnikResponse register(RegisterRequest request) {

        if (korisnikRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Username je već zauzet."
            );
        }

        Korisnik korisnik = new Korisnik();

        korisnik.setIme(request.getIme());
        korisnik.setPrezime(request.getPrezime());
        korisnik.setUsername(request.getUsername());

        korisnik.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        korisnik.setDatumRodjenja(request.getDatumRodjenja());

        Korisnik sacuvani =
                korisnikRepository.save(korisnik);

        return new KorisnikResponse(sacuvani);
    }

    public AuthResponse login(LoginRequest request) {

        Korisnik korisnik = korisnikRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Pogrešan username ili password."
                        )
                );

        if (!passwordEncoder.matches(
                request.getPassword(),
                korisnik.getPassword())) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Pogrešan username ili password."
            );
        }

        String token = jwtService.generateToken(korisnik);

        return new AuthResponse(
                token,
                korisnik.getIdKorisnik(),
                korisnik.getUsername()
        );
    }
}