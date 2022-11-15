package com.example.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.entity.Utente;
import com.example.service.UtenteService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//FASE DI CONTROLLO DELLE RICHIESTE
//filtra (estrae) il token quando vi sono richieste, generando il context dell'utente
public class AuthFilter extends BasicAuthenticationFilter {

    private final UtenteService utenteService;
    private CustomUserDetailService customUserDetailService;

    public AuthFilter(AuthenticationManager authenticationManager, UtenteService utenteService){
        super(authenticationManager);
        this.utenteService = utenteService;
        this.customUserDetailService = new CustomUserDetailService(this.utenteService);
    }


    //richiamato ad ogni richiesta, non permette di accedere ai path specificati
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(JwtConf.headerParam);
        if (header != null && header.startsWith(JwtConf.prefix)) {
            header = header.replace("Bearer ", "");
            DecodedJWT decoded = JwtConf.verifyJwt(header);
            Utente user = this.utenteService.getUtenteByCodFiscale(decoded.getClaim("sub").asString());  //sub indica il subject del token, ovvero l'utente
            UserDetails userDetails = this.customUserDetailService.loadUserByUsername(decoded.getClaim("sub").asString());
            //settare il securitycontext dell'utente serve per la catena di filtri che viene generata dalle varie richieste
            this.utenteService.findById(user.getIdUtente()).ifPresent(entity -> {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
            });
        }
        chain.doFilter(request, response);
    }
}
