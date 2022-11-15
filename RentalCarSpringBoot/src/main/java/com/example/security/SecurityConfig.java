package com.example.security;

import com.example.service.UtenteService;
import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UtenteService utenteService;
    private UserDetailsService userDetailsService;

    public SecurityConfig(UtenteService utenteService){
        this.utenteService = utenteService;
    }



    public static final String[] ADMIN_URL_MATCHER = {
            "api/utente/showUsers",
            "api/utente/deleteUser/**",
            "api/auto/showCars",
            "api/auto/addORupdCar",
            "api/auto/deleteCar/**",
            "api/pren/approvaPren",
            "api/pren/deletePren/**"
    };


    public static final String[] CUSTOMER_URL_MATCHER = {
            "/api/pren/effettuaPren",
            "/api/pren/getUserPrens/**"
    };


    public static final String[] ALL_URL_MATCHER = {
            "/api/utente/addORupdUser/**",
            "/api/pren/showPrens",
            "api/pren/getPrenById/**",
            "api/utente/getUserByCodFis/**",
            "api/utente/getUserById/**",
            "api/auto/getCarById/**"
    };



    //si sovrascrive il metodo configure di springsecurity inserendo le specifiche. Ricordare che gli antMatchers devono essere ordinati dal più specifico al più generico
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new AuthFilter(authenticationManager(), this.utenteService), UsernamePasswordAuthenticationFilter.class).authorizeRequests()
                .antMatchers("/", "/api/auto/getCarsDisp/**").permitAll()
                .antMatchers("/api/auth").permitAll()
                .antMatchers(ALL_URL_MATCHER).hasAnyRole("USER", "ADMIN")
                .antMatchers(ADMIN_URL_MATCHER).hasRole("ADMIN")
                .antMatchers(CUSTOMER_URL_MATCHER).hasRole("USER")
                .anyRequest().authenticated();
    }
}
