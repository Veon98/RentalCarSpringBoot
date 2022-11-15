package com.example.dto;

import com.example.entity.Auto;
import com.example.entity.Utente;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class PrenotazioneDto {
    protected int idPren;
    protected int auto;
    protected int utente;
    protected LocalDate dataInizio;
    protected LocalDate dataFine;
    protected LocalDate dataPren;
    protected Boolean isApprovata;


    public int getIdPren() {
        return idPren;
    }

    public void setIdPren(int idPren) {
        this.idPren = idPren;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }

    public int getUtente() {
        return utente;
    }

    public void setUtente(int utente) {
        this.utente = utente;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public LocalDate getDataPren() {
        return dataPren;
    }

    public void setDataPren(LocalDate dataPren) {
        this.dataPren = dataPren;
    }

    @JsonProperty("isApprovata")
    public Boolean getApprovata() {
        return isApprovata;
    }

    public void setApprovata(Boolean approvata) {
        isApprovata = approvata;
    }
}
