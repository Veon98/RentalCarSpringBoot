package com.example.service;

import com.example.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

//l'interfaccia JPARepo contiene delle query già fatte e dà la possibilità di costruirne altre custom
public interface UtenteService extends JpaRepository<Utente, Integer> {

    Utente getUtenteByCodFiscale(String codFiscale);
}
