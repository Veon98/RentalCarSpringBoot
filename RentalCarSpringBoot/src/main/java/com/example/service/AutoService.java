package com.example.service;

import com.example.dto.AutoDto;
import com.example.entity.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

//l'interfaccia JPARepo contiene delle query già fatte e dà la possibilità di costruirne altre custom
public interface AutoService extends JpaRepository<Auto, Integer> {

    @Query("SELECT a FROM Auto a WHERE a.idAuto not in (SELECT distinct p.auto from Prenotazione p where (((p.dataInizio<=?1 and p.dataFine>=?2)or((p.dataInizio between ?1 and ?2) and (p.dataFine between ?1 and ?2))) and (p.isApprovata=true))) and a.isDisponibile=true order by a.idAuto desc ")
    public List<Auto> getCarsDisp(LocalDate dataI, LocalDate dataF);
}
