package com.example.service;

import com.example.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//l'interfaccia JPARepo contiene delle query già fatte e dà la possibilità di costruirne altre custom
public interface PrenService extends JpaRepository<Prenotazione, Integer> {

    @Query("select p from Prenotazione p where p.utente.idUtente = ?1")
    List<Prenotazione> getUserPrens(int idU);

    //in teoria fa ciò che fa la query di cui sopra
    //List<Prenotazione> getPrenotazioneByUtenteIdUtente(int idU);
}
