package com.example.security;

import com.example.entity.Utente;
import com.example.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UtenteService utenteService;


    //ulteriore check su username e password durante il login
    @GetMapping("/getCheck/{username}/{pass}")
    public String getCheck(@PathVariable String username, @PathVariable String pass){
        String check = "";
        Utente user = utenteService.getUtenteByCodFiscale(username);
        String pwd = user.getPwd();
        boolean tmp;
        tmp = passwordEncoder.matches(pass, pwd);
        check = String.valueOf((tmp==true) ? true : false);
        return check;
    }
}
