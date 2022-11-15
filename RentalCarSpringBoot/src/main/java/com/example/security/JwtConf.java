package com.example.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.joda.time.DateTime;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;


//GESTIONE TOKEN
//genera e decodifica il token
@Component
public class JwtConf {

    public static final String issuer = "AndreaDoddis";   //emittente del token
    public static String secret;
    public static String prefix;
    public static String headerParam;


    //sfrutta le variabili specificate nel proprierties per gestire la creazione del token
    @Autowired
    public JwtConf(Environment env) {
        secret = env.getProperty("security.secret");
        prefix = env.getProperty("security.prefix");
        headerParam = env.getProperty("security.param");
        if(secret == null || prefix == null || headerParam == null) {
            throw new BeanInitializationException("Non è stato possibile assegnare le proprietà di security");
        }
    }



    public static String createJwt(String subject, Map<String, Object> payloadClaims) {

        JWTCreator.Builder builder = JWT.create().withSubject(subject).withIssuer(issuer);
        final DateTime now = DateTime.now();
        builder.withIssuedAt(now.toDate()).withExpiresAt(now.plusDays(1).toDate());

        if (payloadClaims.isEmpty()) {
            //log.warn("You are building a JWT without header claims");
        }
        for (Map.Entry<String, Object> entry : payloadClaims.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue().toString());
        }
        return builder.sign(Algorithm.HMAC256(JwtConf.secret));
    }



    public static DecodedJWT verifyJwt(String jwt) {
        return JWT.require(Algorithm.HMAC256(JwtConf.secret)).withIssuer(JwtConf.issuer).build().verify(jwt);
    }
}
