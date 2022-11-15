package com.example.security;

import com.example.entity.Utente;
import com.example.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    UtenteService utenteService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    //entrypoint del sistema di login
    @PostMapping
    public Map<String, Object> toLogin(@RequestBody Credenziali credenziali) {
        /*Utente utente = this.utenteService.getUtenteByCodFiscale(credenziali.getUsername());
        String pwd = utente.getPwd();
        return credenziali.getPassword() == pwd ? this.authService.authLogin(credenziali.getUsername(), credenziali.getPassword()) : null;
        */
        return this.authService.authLogin(credenziali.getUsername(), credenziali.getPassword());
    }

}
