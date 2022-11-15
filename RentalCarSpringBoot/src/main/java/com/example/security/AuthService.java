package com.example.security;

import com.example.entity.Utente;
import com.example.service.UtenteService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

//triggera la generazione del token sfruttando l'username dell'utente
@Service
public class AuthService {

    private UtenteService utenteService;
    private PasswordEncoder pwdEncoder;

    public AuthService(UtenteService utenteService, PasswordEncoder pwdEncoder){
        this.utenteService = utenteService;
        this.pwdEncoder = pwdEncoder;
    }


    public Map<String, Object> authLogin(String codFis, String password) {
        //recupera l'utente per creare un jwt sulla base del suo username
        Utente utente = this.utenteService.getUtenteByCodFiscale(codFis);
        String pwd = utente.getPwd();
        Map<String, Object> claimMap = new HashMap<>(0);
        claimMap.put("role", utente.isAdmin()? "ADMIN" : "USER");
        String jwt = (pwd == utente.getPwd()) ? JwtConf.createJwt(codFis, claimMap) : null;
        claimMap.put("token", jwt);
        return claimMap;
    }
}
