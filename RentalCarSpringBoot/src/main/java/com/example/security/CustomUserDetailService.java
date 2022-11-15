package com.example.security;

import com.example.entity.Utente;
import com.example.service.UtenteService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

//"costruisce" l'utente loggato estendendo userdetailsservice, assegnandogli anche il ruolo
public class CustomUserDetailService implements UserDetailsService {

    private UtenteService utenteService;

    public CustomUserDetailService(UtenteService utenteService){
        this.utenteService = utenteService;
    }


    @Override
    public UserDetails loadUserByUsername(String codFis) {

        Utente utente = utenteService.getUtenteByCodFiscale(codFis);
        User.UserBuilder builder = User.withUsername(utente.getCodFiscale());
        builder.password(utente.getPwd());

        if (utente.isAdmin()==true){	//decido il ruolo dell'utente loggato controllando il suo booleano
            builder.roles("ADMIN");  //diviene ROLE_ADMIN
        }
        else {
            builder.roles("USER");   //diviene ROLE_USER
        }

        return builder.build();
    }
}
